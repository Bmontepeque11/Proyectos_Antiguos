from django.shortcuts import render
from django.http import HttpResponse
from django.core.files.storage import FileSystemStorage
import requests

# Create your views here.
def index(request):
    if request.method == "POST":

        uploadedfile = request.FILES['ArchivoCargado']
        fs = FileSystemStorage()
        fs.save(uploadedfile.name, uploadedfile)
        print(uploadedfile.name)
        print(uploadedfile.size)

        #Abrir el XML?
        ArchivoAbierto = uploadedfile.read()
        print(ArchivoAbierto)

        #Pasarle la info al Flask
        DataArchivoXML = {uploadedfile.name:uploadedfile}
        resp = requests.post('http://localhost:5000', DataArchivoXML)

    return render(request, 'index.html')