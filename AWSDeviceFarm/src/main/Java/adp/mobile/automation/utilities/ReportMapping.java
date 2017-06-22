package adp.mobile.automation.utilities;

public class ReportMapping{

	private String machineName = "";
	private String browserName = "";
    private String browserVersion = "";
    private String testName = "";
    private String SSN = "";
    private String status = "Not Available";
    private String duration = "";
    
    
    public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}	
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return "ReportMapping [machineName=" + machineName + ", browserName=" + browserName + ", browserVersion="
				+ browserVersion + ", testName=" + testName + ", SSN=" + SSN + ", status=" + status + ", duration="
				+ duration + "]";
	}
	
}
