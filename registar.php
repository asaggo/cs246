<?php
    $con = mysqli_connect("localhost", "id1915312_jessie", "cs246", "id1915312_byui_testing_center");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    $firstname = $_POST["firstname"];
    $lastname = $_POST["lastname"];
    $isAdmin = $_POST["isAdmin"];
 
    $statement = mysqli_prepare($con, "INSERT INTO user (username, password, firstname, lastname, isAdmin) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $username, $password, $firstname, $lastname, $isAdmin);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>