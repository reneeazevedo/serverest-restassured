package br.com.serverest.listeners;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
@Log4j2
public class TestngListeners implements ITestListener {
    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream requestVar = new PrintStream(request, true);
    private PrintStream responseVar = new PrintStream(request, true);


    public void onStart(ITestContext context) {
        log.info(context.getName());
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL,responseVar),new RequestLoggingFilter(LogDetail.ALL, requestVar));
    }

    public void onFinish(ITestContext context) {
        log.info(context.getEndDate());
    }

    public void onTestStart(ITestResult result) {
        log.info("Teste Iniciado: " +result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        log.info("Test executed with success: "+result.getName());
        logRequest(request);
        logResponse(response);

    }
    @Attachment(value = "request2")
    public byte[] logRequest(final  ByteArrayOutputStream stream){
        return attach(stream);
    }
    @Attachment(value = "response2")
    public byte[] logResponse(final  ByteArrayOutputStream stream){
        return attach(stream);
    }

    public byte[] attach(final ByteArrayOutputStream log){
        final byte [] array = log.toByteArray();
        log.reset();
        return array;
    }

    public void onTestFailure(ITestResult result) {
        log.info("onTestFailure Method" +result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        log.info("onTestSkipped Method" +result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("onTestFailedButWithinSuccessPercentage" +result.getName());
    }

}
