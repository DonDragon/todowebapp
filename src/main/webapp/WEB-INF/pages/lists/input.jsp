<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new todo list</title>
</head>
<body>
<h2>Add new todo</h2>
<form method="post" action="addlist">
    <input name="title" type="text" class="form-control" placeholder="Enter title" />
      <br/>
     <textarea name="description" class="form-control" placeholder="Enter description" rows="5" > </textarea>
        <br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form>
</body>
</html>