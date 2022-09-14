package com.example.printer_test;

import TremolZFP.FP;
import TremolZFP.OptionDisplay;
import TremolZFP.OptionReceiptFormat;
import TremolZFP.OptionVATClass;

public class FPrint {
    public static void createReceipt() {
        try {
            FP fp = new FP();
            fp.ServerAddress = "http://localhost:4444/";
            fp.ServerSetDeviceSerialPortSettings("KRAMW008202110009306", 115200);
            fp.ApplyClientLibraryDefinitions();
             fp.OpenReceipt(OptionReceiptFormat.Detailed, "01");
             fp.SellPLUfromExtDB("MY AWESOME PRODUCT", OptionVATClass.VAT_Class_A, 200d,"pcs","", "", 10d, -2d, -1d);
             fp.Subtotal( OptionDisplay.Yes, null, 4.15d);
             fp.CloseReceipt();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
