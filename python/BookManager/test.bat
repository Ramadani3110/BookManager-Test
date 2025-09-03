:: This is for running coverage automaticly and report html automaticly ::
@echo off

echo.
echo ===============================================
echo Running unit tests with coverage...
echo ===============================================
echo.
python -m coverage run -m unittest discover -s test

echo.
echo ===============================================
echo Displaying coverage report in terminal...
echo ===============================================
echo.
python -m coverage report

echo.
echo ===============================================
echo Generating coverage report in HTML format...
echo ===============================================
echo.
python -m coverage html

echo.
echo ==============================================
echo Done! Open htmlcov\index.html to view the coverage report.
echo ==============================================
echo.