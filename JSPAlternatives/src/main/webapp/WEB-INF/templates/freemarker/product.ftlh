<html>
<head>
<title>Product</title>
</head>

<body>
<h1>Freemarker Example: Listing products</h1>
<h2>Hi, ${userName}!</h2>
<ul>
 <#list products as product >
  <li><a href="${product.url}">${product.name}</a></li>
 </#list>
</ul>
</body>

</html>