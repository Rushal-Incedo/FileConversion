<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>

	<div method="POST" action="uploadFile" enctype="multipart/form-data">
		<!-- File to upload: <input type="file" name="file"><br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Upload"> Press here to upload the file! -->
		
	</div>
	<button type="button" id="imageId" onClick="location.href='/SpringFileUpload/pdf/${fileName}'" >download</button>
    </body>
</html>