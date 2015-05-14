var $countA = 0;
function countUpA() {
  document.getElementById( "sampleOutputA" ).innerHTML = ++$countA;
}

function showAndroidToast( message ) {
  alert( "Toast/" + message );
}

function showAndroidDialog( title, message, posButtonStr, neuButtonStr, negButtonStr ) {
  alert( "Dialog/" + title + "/" + message + "/" + posButtonStr + "/" + neuButtonStr + "/" + negButtonStr );
}
