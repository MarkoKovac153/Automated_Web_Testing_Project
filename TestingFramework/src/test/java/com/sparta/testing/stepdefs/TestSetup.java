package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Locale;

public class TestSetup {
    private static GeckoDriverService firefoxService;
    private static ChromeDriverService chromeService;
    private static EdgeDriverService edgeService;
    private static WebDriver webDriver;
    private static String myOS;
    private static String myBrowser;

    private static String detectOS() {
        String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (os.contains("win")) {
            return "windows";
        } else if (os.contains("mac")) {
            return "mac";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return "linux";
        } else {
            return "unknown";
        }
    }

    private static String detectDefaultBrowser() {
        myOS = detectOS();
        String browser = null;

        try {
            browser = switch (myOS) {
                case "windows" -> getDefaultBrowserWindows();
                case "mac" -> getDefaultBrowserMacOs();
                case "linux" -> getDefaultBrowserLinux();
                default -> throw new UnsupportedOperationException("Unsupported operating system: " + myOS);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        return browser;
    }

    private static String getDefaultBrowserWindows() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "reg", "query",
                "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\Shell\\Associations\\UrlAssociations\\https\\UserChoice",
                "/v", "ProgId");
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("ProgId")) {
                if (line.contains("ChromeHTML")) {
                    return "chrome";
                } else if (line.contains("FirefoxURL")) {
                    return "firefox";
                } else if (line.contains("IE.HTTP")) {
                    return "ie";
                } else if (line.contains("MSEdgeHTM")) {
                    return "edge";
                } else if (line.contains("OperaStable")) {
                    return "opera";
                }
            }
        }
        process.waitFor();
        return null;
    }

    private static String getDefaultBrowserMacOs() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "defaults", "read",
                "com.apple.LaunchServices/com.apple.launchservices.secure", "LSHandlers",
                "-array");
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("http")) {
                if (line.contains("chrome")) {
                    return "chrome";
                } else if (line.contains("firefox")) {
                    return "firefox";
                } else if (line.contains("safari")) {
                    return "safari";
                } else if (line.contains("microsoft edge")) {
                    return "edge";
                } else if (line.contains("opera")) {
                    return "opera";
                }
            }
        }
        process.waitFor();
        return null;
    }

    private static String getDefaultBrowserLinux() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("xdg-settings", "get", "default-web-browser");
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = reader.readLine();
        process.waitFor();

        if (line != null) {
            if (line.contains("chrome")) {
                return "chrome";
            } else if (line.contains("firefox")) {
                return "firefox";
            } else if (line.contains("microsoft-edge")) {
                return "edge";
            } else if (line.contains("opera")) {
                return "opera";
            }
        }
        return null;
    }

    private static String getDriverLocation() {
        String driverLocation = null;

        switch (myBrowser.toLowerCase()) {
            case "firefox" -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/geckodriver.exe";
                    case "mac" -> "src/test/resources/drivers/geckodriver-mac";
                    case "linux" -> "src/test/resources/drivers/geckodriver-linux";
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case "chrome" -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/chromedriver.exe";
                    case "mac" -> "src/test/resources/drivers/chromedriver-mac";
                    case "linux" -> "src/test/resources/drivers/chromedriver-linux";
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case "edge" -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/msedgedriver.exe";
                    case "mac", "linux" ->
                            throw new UnsupportedOperationException("Edge is only supported on Windows.");
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case "opera" -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/operadriver.exe";
                    case "mac" -> "src/test/resources/drivers/operadriver-mac";
                    case "linux" -> "src/test/resources/drivers/operadriver-linux";
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case "safari" -> {
                if (!myOS.equals("mac")) {
                    throw new UnsupportedOperationException("Safari is only supported on macOS.");
                }
                driverLocation = "/usr/bin/safaridriver";
            }
            default -> throw new UnsupportedOperationException("Unsupported browser: " + myBrowser);
        }
        return driverLocation;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--start-maximized");
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        return options;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--start-maximized");
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless", "--start-maximized");
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        return options;
    }

    private static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(true);
        return options;
    }

    public static Website getWebsite(String url) {
        webDriver.get(url);
        return new Website(webDriver);
    }

    public static void startServiceWithDefaultBrowser() {
        myBrowser = detectDefaultBrowser();
        startService();
    }

    public static void startServiceWithBrowser(String browser) {
        myBrowser = browser;
        startService();
    }

    private static void startService() {
        if (myBrowser == null) {
            throw new IllegalStateException("Browser not set. Use setBrowser() or set myBrowser directly.");
        }

        String driverLocation = getDriverLocation();
        if (driverLocation != null) {
            System.setProperty("webdriver." + myBrowser.toLowerCase() + ".driver", driverLocation);
        }

        switch (myBrowser.toLowerCase()) {
            case "firefox" -> {
                firefoxService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .build();
                try {
                    firefoxService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Firefox service.", e);
                }
            }
            case "chrome" -> {
                chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .build();
                try {
                    chromeService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Chrome service.", e);
                }
            }
            case "edge" -> {
                edgeService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .build();
                try {
                    edgeService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Edge service.", e);
                }
            }
            case "safari" -> {
                // Safari doesn't require a separate service
            }
            case "opera" -> {
                chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .build();
                try {
                    chromeService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Opera service.", e);
                }
            }
            default -> throw new UnsupportedOperationException("Unsupported browser: " + myBrowser);
        }
    }

    public static void createWebDriver() {
        switch (myBrowser.toLowerCase()) {
            case "firefox" -> webDriver = new RemoteWebDriver(firefoxService.getUrl(), getFirefoxOptions());
            case "chrome", "opera" -> webDriver = new RemoteWebDriver(chromeService.getUrl(), getChromeOptions());
            case "edge" -> webDriver = new RemoteWebDriver(edgeService.getUrl(), getEdgeOptions());
            case "safari" -> webDriver = new SafariDriver(getSafariOptions());
            default -> throw new UnsupportedOperationException("Unsupported browser: " + myBrowser);
        }
    }

    public static void stopService() {
        if (webDriver != null) {
            webDriver.quit();
        }

        if (firefoxService != null && firefoxService.isRunning()) {
            firefoxService.stop();
        }

        if (chromeService != null && chromeService.isRunning()) {
            chromeService.stop();
        }

        if (edgeService != null && edgeService.isRunning()) {
            edgeService.stop();
        }
    }
}
