package co.edu.uniquindio.proyectofinal.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Qr {

    public static void generarQr(String contenido, String rutaArchivo) throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(contenido, BarcodeFormat.QR_CODE, 350, 350);

        Path path = FileSystems.getDefault().getPath(rutaArchivo);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

}
