# 常用的jquery及js方法记录

## findIndex

* 找到数组内*第一个*等于item的元素的*下标*
* 如果没有符合条件的元素返回 -1

```javascript
const list = [0, 3, 5, 7, 8, 1]
//找到list内第一个等于1的元素的下标
let index = _.findIndex(list, function (item) {
    return item === 1
})//index:5
```

```javascript
let ages = [3, 10, 18, 20];

function isAdult() {
    return ages.findIndex(function (age) {
        return age >= 18
    });
}

let index = isAdult()//index:2
```

## each（jquery）

* 遍历数组的每一项

```javascript 
const list = [0, 1, 2, 3, 4]
const newList = []
_.each(list, function (item) {
    newList.push(item + 1)
})
//newList:[1,2,3,4,5]
```

## Array.prototype.map()

* map() 方法创建一个新数组，这个新数组由原数组中的每个元素都调用一次提供的函数后的返回值组成。
* 官方文档：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/map

```javascript
const array1 = [1, 4, 9, 16];

// Pass a function to map
const map1 = array1.map(x => x * 2);

console.log(map1);
// Expected output: Array [2, 8, 18, 32]
```

## ES6箭头函数

* ES6允许使用“箭头”（=>）定义函数,箭头函数表达式的语法比函数表达式更简洁，
  并且没有自己的this，arguments，super或new.target。
  箭头函数表达式更适用于那些本来需要匿名函数的地方，并且它不能用作构造函数。
* 官方文档：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Functions/Arrow_functions

```javascript
function fun(a) {
    return a;
}

//去掉 function 在 ( ) 和 { } 之间添加 => 
var fun = (a) => {
    return a;
}
//如果只有一个形参可省略 ( ) 如果一个形参都没有，必须加( )         
var fun = a => {
    return a;
}
//如果函数体只有一句话，可省略{ }，如果仅有的这句话还是 return， 则必须省略 return         
var fun = a => a
```

* 使用注意的点

1. 箭头函数会改变函数内 this 的指向与上级作用域中的this指向保持一致。
2. 不可用当做构造函数，也就是说不能用new调用。
3. 不能在里边使用 arguments 对象，该对象在箭头函数中不存在，但还是可用使用剩余参数代替。（剩余参数）

# dom元素

## document选择器

```html

<body>
<div id="query">这里</div>
<ul id="list">
    <li class="content">1</li>
    <li class="content">2</li>
    <li class="content">3</li>
    <li class="content">4</li>
</ul>
</body>
<script>
    //根据id获取元素
    let ele = document.getElementById('query');
    //查到所有包含class content的元素集合
    let list = document.querySelectorAll('.content');
    //查到第一个class为content的元素
    let firstContent = document.querySelector('.content');
</script>
```

