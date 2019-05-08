call SET_JAVA_HOME.bat
del Generate_Movie_Names.class
del Check_File_Exists.class

"%JAVA_HOME%\bin\javac" Check_File_Exists.java
"%JAVA_HOME%\bin\javac" Generate_Movie_Names.java

"%JAVA_HOME%\bin\java" -cp . -Xmx512m Generate_Movie_Names > movie_names_all.log
"%JAVA_HOME%\bin\java" -cp . -Xmx512m Check_File_Exists    > movie_names_FILE_EXISTS.log
rem PAUSE
