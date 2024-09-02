package com.sparta.testing.stepdefs;

import com.sparta.testing.browser_annotation.Browser;
import com.sparta.testing.browser_annotation.BrowserType;
import com.sparta.testing.pages.Website;
import org.openqa.selenium.JavascriptExecutor;
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
    private static BrowserType myBrowser;

    /**
     * Navigates to the specified URL using the current WebDriver instance and returns a new Website object.
     *
     * @param url The URL to navigate to.
     * @return A Website object initialized with the current WebDriver instance.
     */
    public static Website getWebsite(String url) {
        webDriver.get(url);
        return new Website(webDriver);
    }

    /**
     * Starts the WebDriver service based on the browser type specified by the Browser annotation in the given test class.
     * If no annotation is present, the default browser for the operating system is detected and used.
     *
     * @param testClass The test class containing the Browser annotation.
     * @throws UnsupportedOperationException if the detected default browser is not supported.
     */
    public static void startService(Class<?> testClass) {
        myOS = detectOS();
        Browser annotation = testClass.getAnnotation(Browser.class);

        if (annotation != null) {
            myBrowser = annotation.value();
        } else {
            myBrowser = BrowserType.DEFAULT;
        }

        if (myBrowser == BrowserType.DEFAULT) {
            String defaultBrowser = detectDefaultBrowser().toUpperCase();
            try {
                myBrowser = BrowserType.valueOf(defaultBrowser);
            } catch (IllegalArgumentException e) {
                throw new UnsupportedOperationException("Detected default browser is not supported: " + defaultBrowser, e);
            }
        }

        startService();
    }

    /**
     * Creates a new WebDriver instance based on the previously determined browser type.
     *
     * @throws UnsupportedOperationException if the browser type is not supported.
     */
    public static void createWebDriver() {
        switch (myBrowser) {
            case FIREFOX -> webDriver = new RemoteWebDriver(firefoxService.getUrl(), getFirefoxOptions());
            case CHROME, OPERA -> webDriver = new RemoteWebDriver(chromeService.getUrl(), getChromeOptions());
            case EDGE -> webDriver = new RemoteWebDriver(edgeService.getUrl(), getEdgeOptions());
            case SAFARI -> webDriver = new SafariDriver(getSafariOptions());
            default -> throw new UnsupportedOperationException("Unsupported browser: " + myBrowser);
        }
        webDriver.get("about:blank");
    }

    /**
     * Stops the WebDriver service and quits the WebDriver instance, ensuring all browser processes are terminated.
     */
    public static void stopService() {
        if (webDriver != null) {
            ((JavascriptExecutor) webDriver).executeScript("window.sessionStorage.clear();");
            ((JavascriptExecutor) webDriver).executeScript("window.localStorage.clear();");
            webDriver.quit();
            webDriver = null;
        }

        forceKillDrivers();
    }

    /**
     * Forces the termination of WebDriver processes for various browsers, depending on the operating system.
     *
     * @throws UnsupportedOperationException if the operating system is not supported.
     */
    public static void forceKillDrivers() {
        String[] command = null;
        if (myOS.contains("win")) {
            command = new String[]{"cmd.exe", "/c", "taskkill /F /IM chromedriver.exe /IM geckodriver.exe /IM msedgedriver.exe /IM operadriver.exe"};
        } else if (myOS.contains("mac") || myOS.contains("nix") || myOS.contains("nux")) {
            command = new String[]{"/bin/sh", "-c", "pkill -f chromedriver; pkill -f geckodriver; pkill -f msedgedriver; pkill -f operadriver"};
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + myOS);
        }

        try {
            Process process = new ProcessBuilder(command).start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to force kill WebDriver processes: " + e.getMessage());
        }
    }

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

        switch (myBrowser) {
            case FIREFOX -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/geckodriver.exe";
                    case "mac" -> "src/test/resources/drivers/geckodriver-mac";
                    case "linux" -> "src/test/resources/drivers/geckodriver-linux";
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case CHROME -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/chromedriver.exe";
                    case "mac" -> "src/test/resources/drivers/chromedriver-mac";
                    case "linux" -> "src/test/resources/drivers/chromedriver-linux";
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case EDGE -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/msedgedriver.exe";
                    case "mac", "linux" ->
                            throw new UnsupportedOperationException("Edge is only supported on Windows.");
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case OPERA -> {
                driverLocation = switch (myOS) {
                    case "windows" -> "src/test/resources/drivers/operadriver.exe";
                    case "mac" -> "src/test/resources/drivers/operadriver-mac";
                    case "linux" -> "src/test/resources/drivers/operadriver-linux";
                    default -> throw new UnsupportedOperationException("Unsupported OS: " + myOS);
                };
            }
            case SAFARI -> {
                if (!myOS.equals("mac")) {
                    throw new UnsupportedOperationException("Safari is only supported on macOS.");
                }
                driverLocation = "/usr/bin/safaridriver";
            }
            default -> throw new UnsupportedOperationException("Unsupported browser: " + myBrowser);
        }
        return driverLocation;
    }

    private static void startService() {
        if (myBrowser == null) {
            throw new IllegalStateException("Browser not set. Use startService(Class<?> testClass) or set myBrowser directly.");
        }

        String driverLocation = getDriverLocation();
        if (driverLocation != null) {
            System.setProperty("webdriver." + myBrowser.name().toLowerCase() + ".driver", driverLocation);
        }

        switch (myBrowser) {
            case FIREFOX -> {
                firefoxService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .withTimeout(Duration.ofSeconds(10))
                        .build();
                try {
                    firefoxService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Firefox service.", e);
                }
            }
            case CHROME -> {
                chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .withTimeout(Duration.ofSeconds(10))
                        .build();
                try {
                    chromeService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Chrome service.", e);
                }
            }
            case EDGE -> {
                edgeService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .withTimeout(Duration.ofSeconds(10))
                        .build();
                try {
                    edgeService.start();
                } catch (IOException e) {
                    throw new RuntimeException("Failed to start Edge service.", e);
                }
            }
            case SAFARI -> {
                // Safari doesn't require a separate service
            }
            case OPERA -> {
                chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverLocation))
                        .usingAnyFreePort()
                        .withTimeout(Duration.ofSeconds(10))
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

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximized");
        options.setImplicitWaitTimeout(Duration.ofSeconds(4));
        return options;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-background-timer-throttling");
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-background-timer-throttling");
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        return options;
    }

    private static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(true);
        return options;
    }
}
