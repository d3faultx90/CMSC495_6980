@echo off
goto check_Permissions

:check_Permissions
    echo Administrative permissions required. Detecting permissions...

    net session >nul 2>&1
    if %errorLevel% == 0 (
        echo Success: Administrative permissions confirmed!
		timeout /t 5
		goto install_Jave_TrustStore
    ) else (
        echo Failure: Current permissions inadequate.
		timeout /t 60
		exit
    )

    pause >nul

:install_Jave_TrustStore
echo [ + ] Time to install Java TrustStore ... 
echo [ CAUTION ] Ensure your Java KeyTool.exe is installed here: "C:\Program Files\Java\jdk-16.0.1\bin\keytool.exe"
echo [ + ] I will look for you now ...
echo.
echo RESULTS:
WHERE /F /R "C:\Program Files\Java\jdk-16.0.1" KeyTool.exe
echo.
echo [ WARNING ] If "KeyTool.exe" is not in the location stated above, exit now.
echo [ WARNING ] (continued) Install Java JDK 16.0.1 in the default location.
echo.
set /p nothing="Press enter to continue ..."
echo.
set /p pemPath="Path to AWS globle bundle pem file (global-bunble.pem): "
echo.
echo [ + ] Let me see if the pem file is there ...
dir %pemPath% | findstr Not
echo [ WARNING ] If "File Not Found" is displayed, locate your PEM file and rerun.
echo.
set /p nothing="Press enter to continue ..."
echo.
set /p trustStorePassword="Enter a password for your TrustStore: "
echo.
echo [ + ] Creating Java TrustStore in ...
timeout /t 5
cd "C:\Program Files\Java\jdk-16.0.1\bin\"
keytool.exe -importcert -alias AWSMySQLCACert -file %pemPath% -keystore "C:\Program Files\Java\jdk-16.0.1\bin\SIMS_AWS_TrustStore" -storepass %trustStorePassword%
echo.
echo [ + ] TrustStore created. Save the following. It is needed for your SIMS application:
echo TrustStore location: C:\Program Files\Java\jdk-16.0.1\bin\SIMS_AWS_TrustStore
echo TrustStore password: %trustStorePassword%
echo.
set /p nothing="Press enter to exit ..."
set /p nothing="Are you sure? Did you save everything???"
