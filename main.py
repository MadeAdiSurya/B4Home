import os 
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import io
import tensorflow
from tensorflow import keras
from keras import layers, models
from flask import Flask, request, jsonify
import numpy as np
from datetime import datetime

app = Flask(__name__)

# Load the model from the HDF5 file
model = keras.models.load_model('house_pricing_model.h5')

LB_mean = 276.53960396039605
LT_mean = 237.43267326732672
KT_mean = 4.6683168316831685
KM_mean = 3.607920792079208
GRS_mean = 0.8702970297029703
HARGA_mean = 7628987019.011881

LB_std = 177.86455720422907
LT_std = 179.95760408598153
KT_std = 1.5727757395356827
KM_std = 1.420065965909875
GRS_std = 0.3361428015645967
HARGA_std = 7340945790.215558



def convert_LB(value):
    temp = (value - LB_mean) / LB_std
    return temp

def convert_LT(value):
    temp = (value - LT_mean) / LT_std
    return temp

def convert_KT(value):
    temp = (value - KT_mean) / KT_std
    return temp

def convert_KM(value):
    temp = (value - KM_mean) / KM_std
    return temp

def convert_GRS(value):
    temp = (value - GRS_mean) / GRS_std
    return temp

def convert_HARGA(value):
    temp = (value - HARGA_mean) / HARGA_std
    return temp

def diff_year(value):
    year = datetime.now().year
    temp = abs(value - year)
    return temp * 0.05

def harga_akhir(harga, tahun):
    year = datetime.now().year
    temp = abs(tahun - year)
    return temp * 0.05 * harga

def price_ori_scale(value):
    original_value = abs(value * HARGA_std) + HARGA_mean
    return int(original_value)

def calculate_loan_installment(principal, annual_interest_rate, tenure):
    monthly_interest_rate = annual_interest_rate / 12 / 100
    total_installments = tenure * 12

    # Calculation of monthly installment using loan installment formula
    loan_installment = (principal * monthly_interest_rate) / (1 - (1 + monthly_interest_rate)**-total_installments)

    return loan_installment

def primary_debts(downpayment, houseprice):
    return houseprice- downpayment

def total_price(angsuran, tempo):
    return angsuran *tempo * 12

@app.route('/predict', methods=['POST'])
def predict():
    current_year = datetime.now().year
    k = 0.4423
    data = request.json  # Assuming the data is sent as JSON in the request body

    lb = data.get('lb', None)
    lt = data.get('lt', None)
    kt = data.get('kt', None)
    km = data.get('km', None)
    grs = data.get('grs', None)
    tahun = data.get("tahun", None)

    # Normalize the input data
    lb_norm = convert_LB(lb)
    lt_norm = convert_LT(lt)
    kt_norm = convert_KT(kt)
    km_norm = convert_KM(km)
    grs_norm = convert_GRS(grs)

    # Create the X_new array
    X_new = np.array([[lb_norm, lt_norm, kt_norm, km_norm, grs_norm]])

    # Make the prediction
    prediction = model.predict(X_new)

    # Scale the prediction back to the original price
    scaled_price = round(price_ori_scale(prediction[0, 0]) * k)

    #get year calculation
    rate = diff_year(tahun)

    end_price = round(scaled_price *(1+ rate))

    # Return the result as JSON
    return jsonify({'price_prediction': end_price, 'price_now': scaled_price})

@app.route('/kpr', methods=['POST'])
def calculate_kpr():
    data = request.get_json()

    # Extracting data from the JSON payload
    harga_rumah = data['harga_rumah']
    suku_bunga = data['suku_bunga']
    uang_muka = data['uang_muka']
    jangka_waktu = data['jangka_waktu']

    # Menghitung jumlah pinjaman berdasarkan uang muka
    jumlah_pinjaman = harga_rumah - uang_muka

    # Perform the KPR calculation
    result = round(calculate_loan_installment(jumlah_pinjaman, suku_bunga, jangka_waktu))

    # Calculate additional details
    debts = primary_debts(uang_muka, harga_rumah)
    total = round(total_price(result, jangka_waktu))

    return jsonify({
        'monthly_installment': result,
        'debts': debts,
        'total': total
    }), 200


def inverse_convert_LB(value):
    original_value = abs(value * LB_std) + LB_mean
    return int(original_value)

def inverse_convert_LT(value):
    original_value = abs(value * LB_std) + LB_mean
    return int(original_value)

def inverse_convert_KT(value):
    original_value = abs(value * LB_std) + LB_mean
    return int(original_value)

def inverse_convert_KM(value):
    original_value = abs(value * LB_std) + LB_mean
    return int(original_value)

def inverse_convert_GRS(value):
    original_value = abs(value * GRS_std) + GRS_mean
    return int(original_value)


# Create a dictionary of conversion functions
inverse_convert_functions = {
    'LB': inverse_convert_LB,
    'LT': inverse_convert_LT,
    'KT': inverse_convert_KT,
    'KM': inverse_convert_KM,
    'GRS': inverse_convert_GRS
}

# Inverse model function
def inverse_model(harga_rumah):
    # Modeling Invers
    inverse_model_architecture = models.Sequential([
        layers.Dense(10, activation='relu', input_shape=(1,)),
        layers.Dense(10, activation='relu'),
        layers.Dense(15, activation='relu'),
        layers.Dense(5)
    ])

    # Load Model
    inverse_model_architecture.load_weights('inverse_model.h5', by_name=True)

    # Inversing
    fitur_estimasi = inverse_model_architecture.predict(np.array([[harga_rumah]]))

    return fitur_estimasi.flatten()

def predict_house_type(input_harga):
    harga_calculated = input_harga / 0.443
    harga_rumah_pengguna = convert_HARGA(harga_calculated)
    fitur_estimasi = inverse_model(harga_rumah_pengguna)
    original_outputs = {feature: inverse_convert_functions[feature](fitur_estimasi[i]) for i, feature in enumerate(inverse_convert_functions)}
    return original_outputs

@app.route('/housetype', methods=['POST'])
def predict_house():
    data = request.get_json()
    input_harga = data['input_harga']
    tahun = data['tahun']

    final_price = harga_akhir(input_harga, tahun)
    
    LB_value = 1
    LT_value = 0

    while (LB_value > LT_value):
        temp = predict_house_type(final_price)
        LB_value = temp['LB']
        LT_value = temp['LT']

    # Return only 'LB' and 'LT'
    response = {
        'LB': LB_value,
        'LT': LT_value
    }

    return jsonify(response), 200

if __name__ == '__main__':
    app.run(debug=True)
