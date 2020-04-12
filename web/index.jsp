<%--
  Created by IntelliJ IDEA.
  User: Parvinder
  Date: 4/11/2020
  Time: 6:41 PM
  To change this template use File | Settings | File Templates.
--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<html>
  <head>
    <title></title>
  </head>
  <body>
  <script>
    function onButtonClick(){
      document.getElementById('myDiv').style.display = "block";
    }
  </script>
  <style>
    .button {
      display: inline-block;
      padding: 15px 25px;
      font-size: 60px;
      cursor: pointer;
      text-align: center;
      text-decoration: none;
      outline: none;
      color: #fff;
      background-color: #4CAF50;
      border: none;
      border-radius: 15px;
      box-shadow: 0 9px #999;
    }

    .button:hover {background-color: #3e8e41}

    .button:active {
      background-color: #3e8e41;
      box-shadow: 0 5px #666;
      transform: translateY(4px);
    }
  </style>

  <div align="center">
    <button class="button" onclick="onButtonClick()">
      <a>Country Wise Data</a>
    </button>
    <br/>
    <br/>
    <br/>
    <div id="myDiv" style="display:none">
      <form id="myForm"  action="/getDataByCountryName">
        <h2 >Entry country Name: </h2>
        <input name="countryName" type="input"/>
        <input type="submit"/>
      </form>
    </div>

    <form action="/getDataOfAllCountries" >
      <button class="button" >
        <a>World Data</a>
      </button>
    </form>
  </div>
  </body>
</html>
