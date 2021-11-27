<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>


<!-- 
	<form method="POST" action="uploadFile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Upload"> Press here to upload the file!
	</form> -->
	
	<form method="POST" action="uploadFile" enctype="multipart/form-data">
	<div>
<select name="opt">
    <option selected>Select</option>
    <option value="PdfToDoc">Convert Pdf To Doc</option>
    <option value="DocToPdf">Convert Doc To Pdf</option>
    <option value="Compress">Compress Files</option>
    <option value="SplitPdf">Split Pdf by range</option>
    <option value="Merge">Merge Pdf</option>
</select>
</div>
    <table>
        <tr>
            <td><form:label path="file">Select a file to upload</form:label></td>
            <td><input type="file" name="file" /></td>
             <td><input type="file" name="files" multiple="multiple"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Upload" /></td>
        </tr>
    </table>
</form>


</body>
</html>