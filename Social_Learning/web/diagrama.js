
window.onload = function()
{
document.onkeyup = teclas;
document.onclick=ajuste;
}
function teclas(evObject)
{
                var teclaPulsada = evObject.keyCode;
                console.log("\ntecla:"+ teclaPulsada);
                var activo=canvas.getActiveObject();
                if(!activo)
                    if(teclaPulsada==8||teclaPulsada==46)//borrado de elementos
                        Eliminar();
                else(activo)
                {
                     var seleccion = canvas.getActiveObject();
                     if(seleccion.left+seleccion.width>=canvas.width||seleccion.top+seleccion.height>=canvas.height)
                     {
                        //var enter = new KeyboardEvent("keydown", {which:13,code:'enter',keyCode:13, charCode:13});
                        //document.dispatchEvent(enter);
                        canvas.discardActiveObject();
                        //canvas.renderAll();
                       alert("Por favor no salga del limite:\n -Arrastre el cuadro a una posicion dentro del recuadro de Trabajo\n -presione la tecla Enter para agregar otra linea");
                     }
                     else if(seleccion.left<0||seleccion.top<0)
                     {
                      //  seleccion.top=20;
                        //seleccion.left=50;
                        seleccion.set('selectable', true);
                        canvas.renderAll();
                       alert("Por favor no salga del limite");
                     }
                }
}
function ajuste (evObject)
{
                var activo=canvas.getActiveObject();
                if(activo)
                {
                     var seleccion = canvas.getActiveObject();
                     if(seleccion.left+seleccion.width>=canvas.width-20||seleccion.top+seleccion.height>=canvas.height-20)
                     {
                        //var enter = new KeyboardEvent("keydown", {which:13,code:'enter',keyCode:13, charCode:13});
                        //document.dispatchEvent(enter);
                        canvas.discardActiveObject();
                        canvas.renderAll();
                       alert("Por favor no salga del limite:\n -presione la tecla Enter para agregar otra linea");
                     }
                     else if(seleccion.left<0||seleccion.top<0)
                     {
                        //seleccion.top=0;
                        //seleccion.left=0;
                       alert("Por favor no salga del limite");
                     }
                }
}
//funciones del Canvas
var canvas = new fabric.Canvas('canvas');
        function AgregarImagen()
        {
            if (window.File && window.FileReader && window.FileList)
            {
                var archivo = document.getElementById('img').files[0];
                var lector = new FileReader();
                lector.onload = function (e)
                {
                // Cuando éste evento se dispara, los datos están ya disponibles.
                    var foto=new Image();
                    foto.src=e.target.result;
                    var altura= foto.height;
                    var ancho= foto.width;
                    var imagen = new fabric.Image(foto);
                    imagen.set({width:ancho, height: altura});
                    canvas.add(imagen);
                };
                lector.readAsDataURL(archivo);
            }
            else
            {
                alert('Tu navegador no Soporta las API necesarias');
                return;
            }
        }
        function AgregarTitulo()
        {
            //var titulo = document.getElementById('titulo');
            var texto = new fabric.Textbox('SubTitulo',{
                fontWeight: 'bold',
                fontSize: 40,
                textAlign : 'center',
                width: 200,
                height: 100
            });
            canvas.add(texto);
        }
         function AgregarContenido()
        {
            var texto = new fabric.Textbox('contenido',{
                fontSize: 20,
                textAlign: 'center',
                left: 300,
                top: 100,
                textAlign : 'center',
                width: 200,
                height: 100
            });
            canvas.add(texto);
        }
        function Eliminar()
        {
            var seleccion = canvas.getActiveObject();
            if (seleccion) //si un objeto esta activado
            {
                if (confirm('¿Estas seguro que deseas eliminar esto?'))
                {
                    canvas.remove(seleccion);
                }
            }
        }
        function Finalizar()
        {
            var serie = JSON.stringify(canvas);
            var formulario = document.getElementById("diag");
            var enviar = document.createElement("INPUT");
            enviar.setAttribute("type","hidden");
            enviar.setAttribute("name","info");
            enviar.setAttribute("value", serie );
            formulario.appendChild(enviar);
            formulario.submit();
        }