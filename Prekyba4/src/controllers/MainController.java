package controllers;

import handlers.HistoricalDataResultPanelsHandler;
import handlers.RealTimeBarsDataResultPanelsHandler;
import interfaces.IHistoricalDataHandler;

import java.util.Date;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.UnderComp;
import com.ib.controller.Bar;

public class MainController implements EWrapper {

    private HistoricalDataResultPanelsHandler historicalDataHandler;
    private RealTimeBarsDataResultPanelsHandler realTimeBarsHandler;

    public MainController() {

    }

    public HistoricalDataResultPanelsHandler getHistoricalDataHandler() {
        return historicalDataHandler;
    }

    public void setHistoricalDataHandler(
            HistoricalDataResultPanelsHandler historicalDataHandler) {
        this.historicalDataHandler = historicalDataHandler;
    }
    

    public RealTimeBarsDataResultPanelsHandler getRealTimeBarsHandler() {
        return realTimeBarsHandler;
    }

    public void setRealTimeBarsHandler(
            RealTimeBarsDataResultPanelsHandler realTimeBarsHandler) {
        this.realTimeBarsHandler = realTimeBarsHandler;
    }

    @Override
    public void error(Exception e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(String str) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(int id, int errorCode, String errorMsg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void connectionClosed() {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickPrice(int tickerId, int field, double price,
            int canAutoExecute) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickSize(int tickerId, int field, int size) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickOptionComputation(int tickerId, int field,
            double impliedVol, double delta, double optPrice,
            double pvDividend, double gamma, double vega, double theta,
            double undPrice) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickGeneric(int tickerId, int tickType, double value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickString(int tickerId, int tickType, String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickEFP(int tickerId, int tickType, double basisPoints,
            String formattedBasisPoints, double impliedFuture, int holdDays,
            String futureExpiry, double dividendImpact, double dividendsToExpiry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void orderStatus(int orderId, String status, int filled,
            int remaining, double avgFillPrice, int permId, int parentId,
            double lastFillPrice, int clientId, String whyHeld) {
        // TODO Auto-generated method stub

    }

    @Override
    public void openOrder(int orderId, Contract contract, Order order,
            OrderState orderState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void openOrderEnd() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAccountValue(String key, String value, String currency,
            String accountName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePortfolio(Contract contract, int position,
            double marketPrice, double marketValue, double averageCost,
            double unrealizedPNL, double realizedPNL, String accountName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateAccountTime(String timeStamp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void accountDownloadEnd(String accountName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void nextValidId(int orderId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contractDetails(int reqId, ContractDetails contractDetails) {
        // TODO Auto-generated method stub

    }

    @Override
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contractDetailsEnd(int reqId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void execDetails(int reqId, Contract contract, Execution execution) {
        // TODO Auto-generated method stub

    }

    @Override
    public void execDetailsEnd(int reqId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateMktDepth(int tickerId, int position, int operation,
            int side, double price, int size) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateMktDepthL2(int tickerId, int position,
            String marketMaker, int operation, int side, double price, int size) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateNewsBulletin(int msgId, int msgType, String message,
            String origExchange) {
        // TODO Auto-generated method stub

    }

    @Override
    public void managedAccounts(String accountsList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void receiveFA(int faDataType, String xml) {
        // TODO Auto-generated method stub

    }

    @Override
    public void historicalData(int tickerId, String date, double open,
            double high, double low, double close, int volume, int count,
            double wap, boolean hasGaps) {

        IHistoricalDataHandler handler = this.historicalDataHandler
                .getIHistoricalDataHandler(tickerId);

        if (handler != null) {
            if (date.startsWith("finished")) {
                handler.historicalDataEnd();
            } else {
                long longDate;
                if (date.length() == 8) {
                    int year = Integer.parseInt(date.substring(0, 4));
                    int month = Integer.parseInt(date.substring(4, 6));
                    int day = Integer.parseInt(date.substring(6));
                    longDate = new Date(year - 1900, month - 1, day).getTime() / 1000;
                } else {
                    longDate = Long.parseLong(date);
                }
                Bar bar = new Bar(longDate, high, low, open, close, wap,
                        volume, count);
                handler.historicalData(bar, hasGaps);
            }
        }
        // recEOM();
    }

    @Override
    public void scannerParameters(String xml) {
        // TODO Auto-generated method stub

    }

    @Override
    public void scannerData(int reqId, int rank,
            ContractDetails contractDetails, String distance, String benchmark,
            String projection, String legsStr) {
        // TODO Auto-generated method stub

    }

    @Override
    public void scannerDataEnd(int reqId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void realtimeBar(int reqId, long time, double open, double high,
            double low, double close, long volume, double wap, int count) {
        // TODO Auto-generated method stub

    }

    @Override
    public void currentTime(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fundamentalData(int reqId, String data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deltaNeutralValidation(int reqId, UnderComp underComp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickSnapshotEnd(int reqId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void marketDataType(int reqId, int marketDataType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {
        // TODO Auto-generated method stub

    }

    @Override
    public void position(String account, Contract contract, int pos,
            double avgCost) {
        // TODO Auto-generated method stub

    }

    @Override
    public void positionEnd() {
        // TODO Auto-generated method stub

    }

    @Override
    public void accountSummary(int reqId, String account, String tag,
            String value, String currency) {
        // TODO Auto-generated method stub

    }

    @Override
    public void accountSummaryEnd(int reqId) {
        // TODO Auto-generated method stub

    }

}
