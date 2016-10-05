/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
tinymce.init({selector: "textarea.tinymce", theme: "modern", skin: "lightgray", width: "100%", height: "500px",
    setup: function(editor) {

        editor.on('input', function(e) {

            EditarContenido(editor.getContent());

        });

        editor.on('change', function(e) {
            EditarContenido(editor.getContent());
        });


    }


});


