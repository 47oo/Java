### jQuery对象和DOM对象的相互转换
+ jQuery对象和DOM对象定义变量的风格

      var $variable = jQuery对象;
      var variable = DOM对象;

- jQuery对象与DOM对象的相互转化

      var $cr = $("#cr");
      var cr = $cr[0];//jQuery转化为DOM

      var $cr = $("#cr");
      var cr= $cr.get(0);

      var cr = document.getElementById("cr");
      var $cr = $(cr);
    > 注意事件:DOM对象才能使用DOM的方法,jquery只能使用jquery的对象
