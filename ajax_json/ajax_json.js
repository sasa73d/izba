var indexXML = 0;
var indexJSON = 0;
window.onload = function(){
    var btnObjectTXT = document.getElementById('btn-txt');
    var btnObjectXMLl = document.getElementById('cd-left-xml');
    var btnObjectXMLr = document.getElementById('cd-right-xml');
    var btnObjectJSONl = document.getElementById('cd-left-json');
    var btnObjectJSONr = document.getElementById('cd-right-json');
    btnObjectTXT.addEventListener("click", function() {getAjaxConection("text_ajax.txt", readText);});
    btnObjectXMLl.addEventListener("click", previousXML);
    btnObjectXMLr.addEventListener("click", nextXML); //cd-left-json
    btnObjectJSONl.addEventListener("click", previousJSON);
    btnObjectJSONr.addEventListener("click", nextJSON);
    
}
function menuClick(index) {
    var txtObject = document.getElementsByClassName('ajax-txt');
    var xmlObject = document.getElementsByClassName('ajax-xml');
    var jsonObject = document.getElementsByClassName('ajax-json');
    if (index === 0) {
        txtObject[0].style.display = "block";
        xmlObject[0].style.display = "none";
        jsonObject[0].style.display = "none";
    }
    else if (index === 1) {
        txtObject[0].style.display = "none";
        xmlObject[0].style.display = "block";
        jsonObject[0].style.display = "none";
        getAjaxConection("cd_info.xml", readXML);
        getAjaxConection("cd_info.xml", readDetailsXML);
    }
    else if (index === 2) {
        txtObject[0].style.display = "none";
        xmlObject[0].style.display = "none";
        jsonObject[0].style.display = "block";
        getAjaxConection("text_json1.txt", readJSON);
        getAjaxConection("text_json1.txt", readDetailsJSON);
    }
    else if (index === 3) {
        
    }
}

function getAjaxConection(url, readContent) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4) {
            var status = xhttp.status;
            if ((status >= 200 && status < 300) || status === 304) {
                readContent(this);
            }
            else {
                alert(status);
            }
        }
    }
    xhttp.open("GET", url, true);
    xhttp.send();
}
function readText(xhr) {
    var txtDoc = xhr.responseText;
    document.getElementById('demo').innerHTML = txtDoc;
}
function readXML(xhr) {
    var i, table, txtXML, xmlTag, tableObject;
    txtXML = xhr.responseXML;
    tableObject = document.getElementById('table-xml');
    xmlTag = txtXML.getElementsByTagName('CD');
    table = "<tr><th>TITLE</th><th>ARTIST</th</tr>";
    for (i = 0; i < xmlTag.length; i++) {
        table += '<tr onclick="rowIndexXMLOnClick(this)"><td>' +
        xmlTag[i].getElementsByTagName('TITLE')[0].childNodes[0].nodeValue +
        '</td><td>' +
        xmlTag[i].getElementsByTagName('ARTIST')[0].childNodes[0].nodeValue +
        '</td></tr>';
    }
    tableObject.innerHTML = table;
}
function readJSON(xhr) {
    var i, txtJSON, table, tableObject;
    txtJSON = JSON.parse(xhr.responseText);
    tableObject = document.getElementById('table-json');
    table = "<tr><th>ID</th><th>FIRST NAME</th><th>LAST NAME</th></tr>";
    for (i = 0; i < txtJSON.length; i++) {
        table += '<tr onclick="rowIndexJSONOnClick(this)"><td>' +
        txtJSON[i].ID +
        "</td><td>" +
        txtJSON[i].firstName +
        "</td><td>" +
        txtJSON[i].lastName +
        "</td></tr>";
    }
    tableObject.innerHTML = table;
}
function readDetailsXML(xhr) {
    var i, txtXML, xmlTag, divObject, contentXML;
    txtXML = xhr.responseXML;
    tableObject = document.getElementById('table-json');
    divObject = document.getElementById('cd-text');
    xmlTag = txtXML.getElementsByTagName('CD');
    contentXML = "<b>TITLE:</b>" + "  " +  
    xmlTag[indexXML].getElementsByTagName('TITLE')[0].childNodes[0].nodeValue +
    "<br><b>ARTIST:</b>" + "  " + 
    xmlTag[indexXML].getElementsByTagName('ARTIST')[0].childNodes[0].nodeValue +
    "<br><b>COUNTRY:</b>" + "  " + 
    xmlTag[indexXML].getElementsByTagName('COUNTRY')[0].childNodes[0].nodeValue +
    "<br><b>COMPANY:</b>" + "  " + 
     xmlTag[indexXML].getElementsByTagName('COMPANY')[0].childNodes[0].nodeValue +
    "<br><b>PRICE:</b>" + "  " + 
    xmlTag[indexXML].getElementsByTagName('PRICE')[0].childNodes[0].nodeValue +
    "<br><b>YEAR:</b>" + "  " + 
    xmlTag[indexXML].getElementsByTagName('YEAR')[0].childNodes[0].nodeValue;
    divObject.innerHTML = contentXML;
}
function readDetailsJSON(xhr) {
    var i, txtJSON, contentJSON, divObject;
    txtJSON = JSON.parse(xhr.responseText);
    divObject = document.getElementById('json-text');
    contentJSON = "<b>ID:</b>" + "  " + 
        txtJSON[indexJSON].ID +
        "<br><b>FIRST NAME:</b>" + "  " + 
        txtJSON[indexJSON].firstName +
        "<br><b>LAST NAME:</b>" + "  " + 
        txtJSON[indexJSON].lastName +
        "<br><b>AGE:</b>" + "  " + 
        txtJSON[indexJSON].age +
        "<br><b>FAVORITE FOOD:</b>" + "  " + 
        txtJSON[indexJSON].favoriteFood +
        "<br><b>CARS:</b>";
        for (i = 0; i < txtJSON[indexJSON].cars.length; i++) {
           contentJSON += "<br><b>MODEL:</b>" + "  " + txtJSON[indexJSON].cars[i].model + "  " +
            "<b>COLORS:</b>" + "  " + txtJSON[indexJSON].cars[i].colors ;
        } 
    divObject.innerHTML = contentJSON;    
}
function nextXML(){
    var tableObject = document.getElementById('table-xml');
    if (indexXML < tableObject.rows.length - 2){
        indexXML++;
        getAjaxConection("cd_info.xml", readDetailsXML);
    }
}
function previousXML() {
    if(indexXML > 0) {
        indexXML--;
        getAjaxConection("cd_info.xml", readDetailsXML);
    }
}
function nextJSON(){
    var tableObject = document.getElementById('table-json');
    if (indexJSON < tableObject.rows.length - 2){
        indexJSON++;
        getAjaxConection("text_json1.txt", readDetailsJSON);
    }
}
function previousJSON() {
    if(indexJSON > 0) {
        indexJSON--;
        getAjaxConection("text_json1.txt", readDetailsJSON);
    }
}
function rowIndexXMLOnClick(row) {
    debugger
    indexXML = row.rowIndex - 1;
    getAjaxConection("cd_info.xml", readDetailsXML);
}
function rowIndexJSONOnClick(row) {
    indexJSON = row.rowIndex - 1;
    getAjaxConection("text_json1.txt", readDetailsJSON);
}
// onclick="rowindexXMLOnClick()"
    //var menuObject = document.getElementsByTagName('menu-list');