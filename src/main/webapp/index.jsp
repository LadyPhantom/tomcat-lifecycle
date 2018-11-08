<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello World</title>
</head>
<body>
   <h2>Hello World!</h2>

    <form method="post" action="/todo/ajoute">

        <div>
            <label for="afaire">Ajouter:</label>
            <input type="text" name="afaire" id="afaire">
        </div>

        <div>
            <label for="afaire">Global?</label>
            <input type = "checkbox" name = "afaire" value = "1"> Oui
        </div>

        <input type="submit"/>
    </form>
</body>
</html>