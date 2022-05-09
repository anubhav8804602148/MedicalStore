var sellApp = angular.module("sellApp", []);
var sellController = function($scope){

    $scope.addRow = function(){
        var itemRow = document.getElementById("hiddenRow").cloneNode(true);
        itemRow["style"].display = "";
        document.getElementById("sellTableList").appendChild(itemRow);
    }

    $scope.submitPurchases = function(){
        var fullData = {};
        var sellTable = document.getElementById("sellTableList");
        var allRows = sellTable.getElementsByTagName("tr");
        var rowLen = allRows.length;
        
        fullData.customerName= document.getElementById("sellCustomerName").value;
        fullData.transactionDate = Date.now();
        fullData.allTransactions = [];
        for(var i=0;i<rowLen;i++) if(allRows[i].style.display != "none"){
            var allInputFields = allRows[i].getElementsByTagName("input");
            var transaction ={};
            transaction.productName = allInputFields[0].value;
            transaction.batchNumber = allInputFields[1].value;
            transaction.quantity = allInputFields[2].value;
            transaction.sellingPrice = allInputFields[3].value;
            transaction.amount = allInputFields[4].value;
            fullData.allTransactions[i] = transaction;
        }
        console.log(fullData);
        fetch("processTransaction/", {
            headers:{
                "Content-Type":	"application/json"
            },
            body:JSON.stringify(fullData),
            method:"POST"
        })
        .then(response => response.json())
        .then(json => showBill(json));        
    }
    var showBill = function(json){
        window.open("/bills/"+json);
    }
}

sellApp.controller("sellController", sellController);