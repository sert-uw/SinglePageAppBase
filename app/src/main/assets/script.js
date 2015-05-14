var $countA = 0;
function countUpA( num ) {
  $countA += num;
  document.getElementById( "sampleOutputA" ).innerHTML = $countA;
}

function showAndroidToast( message ) {
  alert( "Toast/" + message );
}

function showAndroidDialog( title, message, posStr, neuStr, negStr, posJs, neuJs, negJs ) {
  alert( "Dialog/" + title + "/" + message + "/" + posStr + "/" + neuStr + "/" + negStr + "/" + posJs + "/" + neuJs + "/" + negJs );
}
