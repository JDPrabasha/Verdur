x = 2;
y = 3;

function f1() {
  function f2() {
    x = 11;
    f3();
    function f3() {
      y = x;
      console.log(x + "|" + y);
    }
    x = 7;
    console.log(x + "|" + y);
    f2();
  }
}

f1();
console.log(x + "|" + y);
