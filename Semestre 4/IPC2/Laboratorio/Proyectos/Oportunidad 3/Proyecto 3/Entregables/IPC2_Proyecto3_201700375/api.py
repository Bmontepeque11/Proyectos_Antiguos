from flask import Flask, request, Response, jsonify
from flask_cors import CORS

app = Flask(__name__)
cors = CORS(app, resources={r"/*": {"origin": "*"}})

@app.route('/')
def index():
    return '<h1>Hola Mundo</h1>'

@app.route('/events/', methods=['POST'])
def post_events():
    data = open('data.txt','w+')
    data.write(request.data.decode('utf-8'))
    data.close()

    TXTData = open('data.txt', 'r+')
    TXTDataReal = TXTData.read()
    print('ESTE ES EL POST: ' + str(TXTDataReal))
    TXTData.close()
    
    return Response(response=TXTDataReal,
                    mimetype='text/plain',
                    content_type='text/plain')

@app.route('/events/', methods=['GET'])
def get_events():
    data = open('data.txt','r+')

    TXTData = open('data.txt', 'r+')
    TXTDataReal = TXTData.read()
    print('ESTE ES EL GET: ' + str(TXTDataReal))
    TXTData.close()

    return Response(response=data.read(),
                    mimetype='text/plain',
                    content_type='text/plain')


if __name__ == '__main__':
    app.run(debug=True, port=5000)