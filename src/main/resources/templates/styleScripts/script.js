var addRow = function(){
    var sellRow = document.getElementsByClassName("sellItemsListEntity")[0].cloneNode(true);
    document.getElementById("sellItemsList").appendChild(sellRow);
}

var searchInventory = function(){
    var idSearchIventory = document.getElementById("idSearchIventory");
    var nameSearchIventory = document.getElementById("nameSearchIventory");
    var quantitySearchIventory = document.getElementById("quantitySearchIventory");
    var batchNumberSearchIventory = document.getElementById("batchNumberSearchIventory");
    var costPriceSearchIventory = document.getElementById("costPriceSearchIventory");
    var sellingPriceSearchIventory = document.getElementById("sellingPriceSearchIventory");
    var mfgDateSearchIventory = document.getElementById("mfgDateSearchIventory");
    var purchasingDateSearchIventory = document.getElementById("purchasingDateSearchIventory");
    var expiryDateSearchIventory = document.getElementById("expiryDateSearchIventory");

    var searchFields = [idSearchIventory,nameSearchIventory,quantitySearchIventory,
        batchNumberSearchIventory,costPriceSearchIventory,sellingPriceSearchIventory,
        mfgDateSearchIventory,purchasingDateSearchIventory,expiryDateSearchIventory];

    var allRows = document.getElementsByClassName("inventoryTable")[0].getElementsByTagName("tbody")[0].getElementsByTagName("tr");
    var rowLen = allRows.length;
    for(var i=0;i<rowLen;i++){
        var currRow = allRows[i];
        var match=true;
        for(var j=0;j<9;j++){
            if(!currRow.getElementsByTagName("td")[j].innerHTML.includes(searchFields[j].value)){
                match=false;
            }
        }
        if(!match){
            currRow.style.display="none";
        }
        else{
            currRow.style.display="";
        }
    }
}

var printBill = function(id){
    fetch("/bills/"+id)
    .then(json => console.log(json));
    open("/bills/"+id, "Print Bill", "status=1");
}

var fillItemBatchId = function(row){
    var inputs = row.offsetParent.parentElement.getElementsByTagName("input");
    var nameField = inputs[0];
    var batchField = inputs[1];
    fetch("getProductBatchNumber/"+nameField.value)
    .then(res => res.json()) 
    .then(json => batchField.value=json)
    inputs[4].value=inputs[3].value*inputs[2].value;
}
// ===============================================================================================
// var submitAllItemsForPurchase = function(){
//     var fullData = {};
//     fullData.customerName = document.getElementById("sellCustomerName").value;
//     fullData.allRows = [];
//     var fullItemList = document.getElementById("sellItemsList").children
//     var itemLen = fullItemList.length
//     for(var i=0;i<itemLen;i++){
//         var allInputFields = fullItemList[i].getElementsByTagName("input");
//         var inputLen = allInputFields.length;
//         var singleRow = {}
//         singleRow.productName = allInputFields[0].value;
//         singleRow.quantity = allInputFields[1].value;
//         singleRow.batchNo = allInputFields[2].value;
//         singleRow.sellingPrice = allInputFields[3].value;
//         fullData.allRows += [JSON.stringify(singleRow)];
//     }
    
//     fetch("https://127.0.0.1/processTransaction/",{
//         method:"POST",
//         body:JSON.stringify({
//             "fullData": fullData
//         }),
//         headers:{
//             'Access-Control-Allow-Headers':'*'
//         }

//     }).catch(err => {
//         console.log(err)
//     })
// }