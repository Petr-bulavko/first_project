<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Films</title>
</head>
<body>
<h3>New film</h3>
<form method="post" action="/film/create">
    <label>Name</label><br>
    <input name="filmName"/><br><br>
    <label>Year</label><br>
    <select name="filmYear" required>
        <option value="2000"> 2000 </option>
        <option value="2001"> 2001 </option>
        <option value="2002"> 2002 </option>
        <option value="2003"> 2003 </option>
        <option value="2004"> 2004 </option>
        <option value="2005"> 2005 </option>
        <option value="2006"> 2006 </option>
        <option value="2007"> 2007 </option>
        <option value="2008"> 2008 </option>
        <option value="2009"> 2009 </option>
        <option value="2010"> 2010 </option>
        <option value="2011"> 2011 </option>
        <option value="2012"> 2012 </option>
        <option value="2013"> 2013 </option>
        <option value="2014"> 2014 </option>
        <option value="2015"> 2015 </option>
        <option value="2016"> 2016 </option>
        <option value="2017"> 2017 </option>
        <option value="2018"> 2018 </option>
        <option value="2019"> 2019 </option>
        <option value="2020"> 2020 </option>
        <option value="2021"> 2021 </option>
    </select><br><br>
    <label>Genre</label><br>
    <select name="filmGenre" required>
        <option value="Drama"> Драма </option>
        <option value="Comedy"> Комедия </option>
        <option value="Romance"> Мелодрама </option>
        <option value="Action"> Боевик </option>
        <option value="Horror"> Ужасы </option>
        <option value="Cartoon"> Мультфильм </option>
        <option value="Science fiction"> Научная фантастика </option>
        <option value="Documentary"> Документальный фильм </option>
        <option value="Thriller"> Триллер </option>
        <option value="Adventure"> Приключения </option>
        <option value="Biography"> 	Биографиия </option>
        <option value="family"> Семейный фильм </option>
    </select><br><br>
    <label>Country</label><br>
    <select name="filmCountry" required>
        <option value="USA"> США </option>
        <option value="Germany"> Германия </option>
        <option value="France"> Франция </option>
        <option value="Belgium"> Бельгия </option>
        <option value="Russia"> Россия </option>
        <option value="Ukraine"> Украина </option>
        <option value="India"> Индия </option>
        <option value="UK"> Великобритания </option>
    </select><br><br>
    <label>Description</label><br>
    <textarea inputmode="text" name="description" style="width: 624px; height: 200px;" required></textarea><br><br>
    <input type="submit" value="Save"/>
</form>
</body>
</html>
