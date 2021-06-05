from flask import Flask, request, Response

app = Flask(__name__)

@app.route('/', methods=['POST'])
def post_events():
    data = open('ArchivoChido.csv', 'r+')
    CosasPorEscribir = request.data.decode('utf-8')
    data.write(CosasPorEscribir)
    print("Escribiendo esto: " + CosasPorEscribir)
    data.close()
    return Response(response=request.data.decode('utf-8'), mimetype='text/plain', content_type='text/plain')

@app.route('/', methods=['GET'])
def get_events():
    data = open('ArchivoChido.csv', 'r+')
    print("Este es el método GET")
    return Response(response=data.read(), mimetype='text/plain', content_type='text/plain')

@app.route('/', methods=['UPDATE'])
def update_events():
    data = open('ArchivoChido.csv', 'r+')
    CosasPorEscribir = request.data.decode('utf-8')
    data.write(CosasPorEscribir)
    print("Actualizando esto: " + CosasPorEscribir)
    data.close()
    return Response(response=request.data.decode('utf-8'), mimetype='text/plain', content_type='text/plain')

@app.route('/', methods=['DELETE'])
def delete_events():
    data = open('ArchivoChido.csv', 'r+')
    CosasPorEscribir = request.data.decode('utf-8')
    data.write(CosasPorEscribir)
    print("Eliminando esto: " + CosasPorEscribir)
    data.close()
    return Response(response=request.data.decode('utf-8'), mimetype='text/plain', content_type='text/plain')


if __name__ == '__main__':
    app.run(debug=True, port=5000)