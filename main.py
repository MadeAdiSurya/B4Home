from flask import Flask, request, jsonify

app = Flask(__name__)

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

if __name__ == '__main__':
    app.run(debug=True)