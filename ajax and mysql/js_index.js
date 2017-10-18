
window.onload = function () {
	var sqlObject = {index:0};
	var btnContentPrevious = document.getElementById('db-left');
    var btnContentNext = document.getElementById('db-right');
	var btnContentUpdate = document.getElementById('db-update');
	var btnContentNew = document.getElementById('db-new');
	var btnRecord = document.getElementById('txt-submit');
	var txtCustomerId = document.getElementById('customerID');
	var txtCompanyName = document.getElementById('companyName');
	var txtContactName = document.getElementById('contactName'); 
	btnContentPrevious.addEventListener("click", function() {previous_nextAjaxDbContent(0);});
	btnContentNext.addEventListener("click", function() {previous_nextAjaxDbContent(1);});
	btnContentUpdate.addEventListener("click", function() {btnUpdateNew(0);});
	btnContentNew.addEventListener("click", function() {btnUpdateNew(1);});
	btnRecord.addEventListener("click", function() {btnRecordUpdateNew();});
	txtCustomerId.addEventListener("keyup", searchAjaxDb);
	txtCompanyName.addEventListener("keyup", searchAjaxDb);
	txtContactName.addEventListener("keyup", searchAjaxDb);
	
	//load data
    setSQLRequest(sqlObject);
	
}
// btn previous next
function previous_nextAjaxDbContent(indexPreviousNext) { 
	sqlObject = {CustomerID:"", index:0};
	var tableObjAjaxDb = document.getElementById("table-db");
	var focusRow = document.getElementById('row-br');
	var focusRowValue = Number(focusRow.innerHTML);
	var rowLength = tableObjAjaxDb.rows.length;

	if (focusRowValue > 1 && indexPreviousNext == 0) {
		focusRowValue -= 1;
		sqlObject.CustomerID = tableObjAjaxDb.rows[focusRowValue].cells[0].innerHTML;
		sqlObject.index = 1;
		setSQLRequest(sqlObject);
	}
	else if (focusRowValue < rowLength -1 && indexPreviousNext == 1){
		focusRowValue += 1;
		sqlObject.CustomerID = tableObjAjaxDb.rows[focusRowValue].cells[0].innerHTML;
		sqlObject.index = 1;
		setSQLRequest(sqlObject);
	}
	focusRow.innerHTML = focusRowValue;
}
//select menu	
function menuClick(index) {
	var sqlObject = {index:0};
    var ajaxDbObject = document.getElementsByClassName('ajax-db'); 
	var displayHTML =  document.getElementById('html-code');
	var displayCSS =  document.getElementById('css-code');
	var displayJS =  document.getElementById('js-script');
    if (index === 0) {
        ajaxDbObject[0].style.display = "block";
		displayHTML.style.display = "none";
		displayCSS.style.display = "none";
		displayJS.style.display = "none";
    }
    else if (index === 1) {
        ajaxDbObject[0].style.display = "none";
		displayHTML.style.display = "block";
		displayCSS.style.display = "none";
		displayJS.style.display = "none";
		sqlObject.index = 7;
		setSQLRequest(sqlObject);
    }
    else if (index === 2) {
        ajaxDbObject[0].style.display = "none";
		displayHTML.style.display = "none";
		displayCSS.style.display = "block";
		displayJS.style.display = "none";
		sqlObject.index = 8;
		setSQLRequest(sqlObject);
    }
    else if (index ===3) {
        ajaxDbObject[0].style.display = "none";
		displayHTML.style.display = "none";
		displayCSS.style.display = "none";
		displayJS.style.display = "block";
		sqlObject.index = 9;
		setSQLRequest(sqlObject);
    }
}
//select row from table
function rowIndexOnClick(currentRow) {
	var sqlObject = {CustomerID:"", CompanyName:"", ContactName:"", index:0};
	var tableObjAjaxDb = document.getElementById("table-db");
	var focusRow = document.getElementById('row-br');
	focusRow.innerHTML = currentRow.rowIndex;
	var currentRowCells = tableObjAjaxDb.rows.item(currentRow.rowIndex).cells;
	sqlObject.CustomerID = currentRowCells.item(0).innerHTML;
	sqlObject.index = 1
	
	setSQLRequest(sqlObject);
	
}
//search data
function searchAjaxDb() {
	var sqlObject = {CustomerID:"", CompanyName:"", ContactName:"", index:0};
	sqlObject.CustomerID = document.getElementById('customerID').value;
	sqlObject.CompanyName = document.getElementById('companyName').value;
	sqlObject.ContactName = document.getElementById('contactName').value;
	
	sqlObject.index = 2;
	setSQLRequest(sqlObject);
}
// XMLHttp conection
function getAjaxConection(url, readContent, method, index, sendData) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    }
    else { //code for IE6
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4) {
            var status = xhttp.status;
            if ((status >= 200 && status < 300) || status === 304) {
                readContent(this, index);
            }
            else {
                alert(status);
            }
        }
    }
    xhttp.open(method, url, true);
	if (method == "POST") {
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(sendData);
	} 
	else {
		xhttp.send();
	}
}
//set color for fokused row
function setColorFocusRow(index) {  
	var i, rowTable, currentRowIndex, focusRow;
	focusRow = Number(document.getElementById('row-br').innerHTML);
	rowTable = document.getElementsByClassName("db-row");
	
	switch (index) {
		case 0:
		case 2:
			currentRowIndex = 0;
		break;
		case 1:
			currentRowIndex = focusRow - 1;
			break;
		case 6:
			currentRowIndex = rowTable.length - 1;
			break;
		default:
			alert("Error  set color");
	}	
	
	for (i = 0; i < rowTable.length; i++) {
		if ( i % 2 == 0) {
			rowTable[i].style.color = "black";
			rowTable[i].style.backgroundColor= "#d9d9d9";
		}
		else {
			rowTable[i].style.color = "black";
			rowTable[i].style.backgroundColor= "";
		}
		if (i == currentRowIndex) {
		rowTable[currentRowIndex].style.color = "pink";
		rowTable[currentRowIndex].style.backgroundColor= "Gray";
		rowTable[currentRowIndex].scrollIntoView(false);
		}	
	}
}	
//query settings for Ajax connection
function setSQLRequest(sqlObject) {
	var urlGet = "ajax_db.php";
	var urlPost = "post_db.php";
	var urlHTML = "index.txt";
	var urlCSS = "css_index.css";
	var urlJS = "js_index.txt";
	var methodGet = "GET";
	var methodPost = "POST";
	var sendData;

	switch (sqlObject.index) {
		case 0:
			getAjaxConection(urlGet, readAjaxDbTable, methodGet, sqlObject.index);
			break;
		case 1:
			urlGet += "?" + "CustomerID=" + sqlObject.CustomerID + "&index=" + sqlObject.index;
			
			getAjaxConection(urlGet, readAjaxDbDetails, methodGet, sqlObject.index);
			break;
		case 2:
			urlGet += "?" + "CustomerID=" + sqlObject.CustomerID + "&CompanyName="
			+ sqlObject.CompanyName+ "&ContactName=" + sqlObject.ContactName + "&index=" + sqlObject.index;
			
			getAjaxConection(urlGet, readAjaxDbTable, methodGet, sqlObject.index);
			break;
		case 3:
			urlGet += "?" + "CustomerID=" + sqlObject.CustomerID + "&index=" + sqlObject.index;
			
			getAjaxConection(urlGet, fillDataForUpdate, methodGet, sqlObject.index);
			break;
		case 4:
			sendData = "CustomerID=" + sqlObject.CustomerID + "&CompanyName=" + sqlObject.CompanyName +
			"&ContactName=" + sqlObject.ContactName + "&ContactTitle=" + sqlObject.ContactTitle +
			"&Address=" + sqlObject.Address + "&City=" + sqlObject.City + "&Region=" + sqlObject.Region + 
			"&PostalCode=" + sqlObject.PostalCode + "&Country=" + sqlObject.Country + 
			"&Phone=" + sqlObject.Phone + "&Fax=" + sqlObject.Fax + "&index=" + sqlObject.index;
			
			getAjaxConection(urlPost, responseForUpdateNew, methodPost,sqlObject.index, sendData);
			break;
		case 5:
			sendData = "CustomerID=" + sqlObject.CustomerID + "&CompanyName=" + sqlObject.CompanyName +
			"&ContactName=" + sqlObject.ContactName + "&ContactTitle=" + sqlObject.ContactTitle +
			"&Address=" + sqlObject.Address + "&City=" + sqlObject.City + "&Region=" + sqlObject.Region + 
			"&PostalCode=" + sqlObject.PostalCode + "&Country=" + sqlObject.Country + 
			"&Phone=" + sqlObject.Phone + "&Fax=" + sqlObject.Fax + "&index=" + sqlObject.index;
			
			getAjaxConection(urlPost, responseForUpdateNew, methodPost, sqlObject.index, sendData);
			break;
		case 6:
			getAjaxConection(urlGet, readAjaxDbTable, methodGet, sqlObject.index);
			break;
		case 7:
			getAjaxConection(urlHTML, displayCode, methodGet, sqlObject.index)
			break;
		case 8:
			getAjaxConection(urlCSS, displayCode, methodGet, sqlObject.index)
			break;
		case 9:
			getAjaxConection(urlJS, displayCode, methodGet, sqlObject.index)
			break;
		default:
			alert("Error sqlObject");
	}
}
//display data in table
function readAjaxDbTable(xhr, index) {
    var i, txtDB, table, tableObject, focusRow;
	focusRow = document.getElementById('row-br');
	focusRow.innerHTML = '1';
    txtDB = JSON.parse(xhr.responseText);
    tableObject = document.getElementById('table-db');
    table = "<thead><tr><th>Customer ID</th><th>Company Name</th><th>Contact Name</th></tr></thead><tbody>";
	if (txtDB.length != 0) {
		for (i = 0; i < txtDB.length; i++) {
			table += '<tr onclick="rowIndexOnClick(this)" class="db-row"><td>' +
			txtDB[i].CustomerID + 
			"</td><td>" +
			txtDB[i].CompanyName + 
			"</td><td>" +
			txtDB[i].ContactName + 
			"</td></tr>";
		}
	}
	table += "</tbody>";
    tableObject.innerHTML = table;
    readAjaxDbDetails(xhr, index);
}
//display detailed data
function readAjaxDbDetails(xhr, index) {
    var  txtDB, divObject, contentDB, pObjDetails, modal, rowLength;
	divObject = document.getElementById('db-text');
    txtDB = JSON.parse(xhr.responseText);
	modal = document.getElementById('idMod');
	pObjDetails = document.getElementById("contextID");
	debugger
	if (modal.style.display == "" || modal.style.display == "none") {
		rowLength = 0;
	}
	else {
		rowLength = txtDB.length - 1;
	}	
    contentDB = "<b>CUSTOMER ID:</b>" + "  " + "<p id='contextID'>";
	if (txtDB.length != 0) {
		contentDB += txtDB[rowLength].CustomerID + "</p>" +
		"<b>Company Name:</b>" + "  " +
		txtDB[rowLength].CompanyName + " " +
		"<br><b>Contact Name:</b>" + "  " +
		txtDB[rowLength].ContactName + " " +
		"<br><b>Contact Title:</b>" + "  " +
		txtDB[rowLength].ContactTitle + " " +
		"<br><b>Address:</b>" + "  " +
		txtDB[rowLength].Address + " " +
		"<br><b>City:</b>" + "  " +
		txtDB[rowLength].City + " " +
		 "<br><b>Region:</b>" + "  " +
		txtDB[rowLength].Region + " " +
		 "<br><b>Postal Code:</b>" + "  " +
		txtDB[rowLength].PostalCode + " " +
		"<br><b>Country:</b>" + "  " +
		txtDB[rowLength].Country + " " +
		"<br><b>Phone:</b>" + "  " +
		txtDB[rowLength].Phone + " " +
		 "<br><b>Fax:</b>" + "  " +
		txtDB[rowLength].Fax;
	}
    divObject.innerHTML = contentDB;
	setColorFocusRow(index);
}
//filling data for update
function fillDataForUpdate(xhr, index) {
	var  txtDB;
    txtDB = JSON.parse(xhr.responseText);
	var customerID = document.getElementById('txt-CustomerID');
	var companyName = document.getElementById('txt-CompanyName');
	var contactName = document.getElementById('txt-ContactName');
	var contactTitle = document.getElementById('txt-ContactTitle');
	var address = document.getElementById('txt-Address');
	var city = document.getElementById('txt-City');
	var region = document.getElementById('txt-Region');
	var postalCode = document.getElementById('txt-PostalCode');
	var country = document.getElementById('txt-Country');
	var phone = document.getElementById('txt-Phone');
	var fax = document.getElementById('txt-Fax');
	
	customerID.value = txtDB[0].CustomerID;
	companyName.value = txtDB[0].CompanyName;
	contactName.value = txtDB[0].ContactName;
	contactTitle.value = txtDB[0].ContactTitle;
	address.value = txtDB[0].Address;
	city.value = txtDB[0].City;
	region.value = txtDB[0].Region
	postalCode.value = txtDB[0].PostalCode;
	country.value = txtDB[0].Country
	phone.value = txtDB[0].Phone;
	fax.value = txtDB[0].Fax;
}
//response from update or new recording
function responseForUpdateNew(xhr, index) {
	var sqlObject = {CustomerID:"", index:0};
	var currentCustomerId = document.getElementById('txt-CustomerID');
	debugger
	var txtDb, message;
	txtDb = xhr.responseText;
	alert(txtDb);
	debugger
	//reload data
	if (index == 4) {
		sqlObject.CustomerID = currentCustomerId.value;
		sqlObject.index = 1;
	}
	else if (index ==5) {
		sqlObject.index = 6;
	}
	setSQLRequest(sqlObject);
}	
//openening form for update or recording
function btnUpdateNew(index) {
	var sqlObject = {CustomerID:"", index:0};
	var currentCustomerId = document.getElementById('contextID');
	var resetForm = document.getElementById('form-data');
	var modal = document.getElementById('idMod');
	if (index == 0 && currentCustomerId.innerHTML != "") {
		var sqlObject = {CustomerID:"", index:0};
		var currentCustomerId = document.getElementById('contextID');
		sqlObject.index = 3;
		sqlObject.CustomerID = currentCustomerId.innerHTML;
		setSQLRequest(sqlObject);
	}
	else if (index == 1) {
		resetForm.reset();
	}
	document.getElementById('txt-CompanyName').focus();
	modal.style.display = "block";
}
//closing form
function closeUpdateNew(index) {
	debugger
	var sqlObject = {CustomerID:"", index:0};
	var resetForm = document.getElementById('form-data');
	var modal = document.getElementById('idMod');
	var customerID = document.getElementById('txt-CustomerID');

    modal.style.display = "none";
}
// btn for update and new entries
function btnRecordUpdateNew() {
	if (validateForm() == true) {
		var sqlObject = {CustomerID:"", CompanyName:"", ContactName:"", ContactTitle:"", 
		Address:"", City:"", Region:"", PostalCode:"", Country:"", Phone:"", Fax:"", index:0, rowIndex:0};
		var resetForm = document.getElementById('form-data');
		var modal = document.getElementById('idMod');
		var customerID = document.getElementById('txt-CustomerID');
		var companyName = document.getElementById('txt-CompanyName');
		var contactName = document.getElementById('txt-ContactName');
		var contactTitle = document.getElementById('txt-ContactTitle');
		var address = document.getElementById('txt-Address');
		var city = document.getElementById('txt-City');
		var region = document.getElementById('txt-Region');
		var postalCode = document.getElementById('txt-PostalCode');
		var country = document.getElementById('txt-Country');
		var phone = document.getElementById('txt-Phone');
		var fax = document.getElementById('txt-Fax');
		
		sqlObject.CustomerID = customerID.value;
		sqlObject.CompanyName = companyName.value;
		sqlObject.ContactName = contactName.value;
		sqlObject.ContactTitle = contactTitle.value;
		sqlObject.Address = address.value;
		sqlObject.City = city.value;
		sqlObject.Region = region.value;
		sqlObject.PostalCode = postalCode.value;
		sqlObject.Country = country.value;
		sqlObject.Phone = phone.value;
		sqlObject.Fax = fax.value;
		debugger
		if (customerID.value != "") {
			if (confirm("Save changes?")) {
				sqlObject.index = 4;
			}
			else {
				modal.style.display = "none";
				return;
			}
			setSQLRequest(sqlObject);
		}
		else {
			sqlObject.index = 5;
			setSQLRequest(sqlObject);
			resetForm.reset();
		}
		
		if (customerID.value != "") {
			setTimeout(closeUpdateNew, 500);

		}

		companyName.focus();
	}
}	
//displaying code
function displayCode(xhr, index) {
	var displayHTML =  document.getElementById('html-code');
	var displayCSS =  document.getElementById('css-code');
	var displayJS =  document.getElementById('js-script');
	var txtScript;

	txtScript = "<pre id='txt-code'><code>";
	txtScript += xhr.responseText;
	txtScript += "</code></pre>";
	switch (index) {
		case 7:
			displayHTML.innerHTML = txtScript;
			break;
		case 8:
			displayCSS.innerHTML = txtScript;
			break;
		case 9:
			displayJS.innerHTML = txtScript;
			break;
		default:
			alert("Error");
	}
}	
//validation entries
function validateForm() {
	var validateMessage = "You must enter:\n";
	var	validateValue = true;
	var customerID = document.getElementById('txt-CustomerID');
	var companyName = document.getElementById('txt-CompanyName');
	var contactName = document.getElementById('txt-ContactName');
	var contactTitle = document.getElementById('txt-ContactTitle');
	var address = document.getElementById('txt-Address');
	var city = document.getElementById('txt-City');
	var region = document.getElementById('txt-Region');
	var postalCode = document.getElementById('txt-PostalCode');
	var country = document.getElementById('txt-Country');
	var phone = document.getElementById('txt-Phone');
	var fax = document.getElementById('txt-Fax');

	if (companyName.value.length < 1 || companyName.value.length > 40 ) {
		validateValue = false;
		validateMessage += "Company Name up to 40 characters\n";
		companyName.style.borderColor = "red";
	}
	else {
		companyName.style.borderColor = "";
	}	
	if (contactName.value.length < 1 || contactName.value.length > 30) {
		validateValue = false;
		validateMessage += " Contact Name up to 30 characters\n"; 
		contactName.style.borderColor = "red";
	} 
	else {
		contactName.style.borderColor = "";
	}
	if (address.value.length < 1 || address.value.length > 60 ) {
		validateValue = false;
		validateMessage += " Address up to 60 characters\n"; 
		address.style.borderColor = "red";
	}
	else {
		address.style.borderColor = "";
	}	
	if (city.value.length < 1 || city.value.length > 15) {
		validateValue = false;
		validateMessage += " City up to 15 characters\n"; 
		city.style.borderColor = "red";
	}
	else {
		city.style.borderColor = "";
	}
	if (postalCode.value.length < 1 || postalCode.value.length > 10) {
		validateValue = false;
		validateMessage += " Postal Code up to 10 characters\n"; 
		postalCode.style.borderColor = "red";
	}
	else {
		postalCode.style.borderColor = "";
	}
	if (country.value.length < 1 || country.value.length > 15) {
		validateValue = false;
		validateMessage += " Country up to 15 characters\n"; 
		country.style.borderColor = "red";
	}
	else {
		country.style.borderColor = "";
	}
	if (phone.value.length < 1 || phone.value.length > 24) {
		validateValue = false;
		validateMessage += " Phone up to 24 characters\n"; 
		phone.style.borderColor = "red";
	}
	else {
		phone.style.borderColor = "";
	}
	if (validateValue == false) {
		alert(validateMessage);
	}
	return validateValue;
}	