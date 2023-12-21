import os 
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import io
import tensorflow
from tensorflow import keras
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

def diff_year(value):
    temp = abs(value - datetime.now().year)
    return temp * 0.05

def price_ori_scale(value):
    original_value = abs(value * HARGA_std) + HARGA_mean
    return int(original_value)


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
    tahun = data.get("tahun")

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

    end_price = scaled_price *(1+ rate) 

    # Return the result as JSON
    return jsonify({'price_prediction': end_price})

if __name__ == '__main__':
    app.run(debug=True)
