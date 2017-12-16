
    var tags = [];
    
    function convertAndSendData(){
    var filesSelected = document.getElementById("inputFileToLoad").files;
    if (filesSelected.length > 0){
        var fileToLoad = filesSelected[0];
 
        var fileReader = new FileReader();
 
        fileReader.onload = function(fileLoadedEvent) 
        {
            //var textAreaFileContents = document.getElementById
            //(
              //  "textAreaFileContents"
            //);
     
            //textAreaFileContents.innerHTML = fileLoadedEvent.target.result;
            console.log("mukodok");
            $.ajax({
                    type : "POST",
                    url : "/test/upload",
                    data : {
                        myArray: tags, //notice that "myArray" matches the value for @RequestParam
                                    //on the Java side
                        string: fileLoadedEvent.target.result,
                        privat: document.getElementById("checkBox").checked,
                        title: document.getElementById("cim").value
                    },
                    success : function(response) {
                       // do something ... 
                    },
                    error : function(e) {
                       alert('Error: ' + e);
                    }
                });
        };
 
        fileReader.readAsDataURL(fileToLoad);
        }
    }
    
    document.getElementById("textAreaFileContents").addEventListener("keypress", function(e){
        var key = e.which || e.keyCode;
        if(key === 13){
            if(tags.length != 0){
                var str = this.value.substring(1, this.value.length);
            }else{
                var str = this.value;
            }
            if( str !== ""){
                tags.push(str);
                document.getElementById("showtags").innerHTML += " " + str;
                console.log(tags);
                this.value = ""; 
            }
        }
    });
    
    document.getElementById("butten").addEventListener("click", function(){
        console.log("mukodok");
            convertAndSendData();
    });
            