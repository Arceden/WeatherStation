<?php
/**
 * Created by PhpStorm.
 * User: Youri van de Geer
 * Date: 05/02/2019
 * Time: 12:39
 */

session_start();
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: /index.php");
    exit;
}


// if($_SESSION["type"] == 1) {
    include('config.php');


    $stmt = $pdo->query("SELECT * FROM user");

    echo "<div class=\"container-fluid\">";
    echo "<div class=\"opmaak_1\">";
    echo "<table align=center  border=\"1\" class=\"gebruikersbeheer-opmaak_2\">";
    echo "<tr>";
    echo "<td colspan=\"13\"><h2 align='center'>User Managament</h2></td>";
    echo "</tr>";
    echo "<tr> <th>Id</th><th>Username</th><th>Type</th>";

    while ($row = $stmt->fetch()) {

        echo "<tr>";

       if ($row['type'] == 1) {
        $rank = "Beheerder";
       }
       if ($row['type'] == 0) {
           $rank = "Gebruiker";
       }

        echo "<td>".$row['idUser'] . "</td>";
        echo "<td>".$row['username'] . "</td>";
        echo "<td>".$rank."</td>";

        echo "<form method='post' action='".htmlspecialchars($_SERVER["PHP_SELF"])."'>";
        echo "<td>"."<input type='submit' name ='delete' value = ' ".$row['idUser']." '>"."</td>";
        echo "</form>";
        echo "</tr>";
        echo "</div>";
        echo "</div>";
    }


    if(isset($_POST["delete"])) {

        $number = $_POST['delete'];
    echo "User "; echo $number; echo " has been removed.";

    $del_query = "DELETE FROM user WHERE idUser = ".$number;

    $pdo->exec($del_query);
    }
// }


?>