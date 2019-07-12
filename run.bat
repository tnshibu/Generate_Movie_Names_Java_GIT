call SP.bat
mvn clean install assembly:single
"%JAVA_HOME%\bin\java" -cp target\Generate_Movie_Names-1.0.jar Generate_Movie_Names 
"%JAVA_HOME%\bin\java" -cp target\Generate_Movie_Names-1.0.jar Check_File_Exists      %1  > movie_names_FILE_EXISTS.log
PAUSE
