import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RunningImage extends JPanel{
    private BufferedImage image;
    private int xPosition = 0;   // Позиция по оси X
    private int yPosition = 0;   // Позиция по оси Y
    private final int squareSize = 200; // Размер стороны квадрата
    private int currentStep = 0; // Текущий шаг (из 4)
    private final int speed = 5; // Скорость перемещения изображения

    public RunningImage() {
        // Загружаем изображение
        try {
            image = ImageIO.read(new File("C:\\Users\\New\\Pictures\\1.jpg")); // Укажите свой путь к изображению
        } catch (IOException e) {
            System.out.println(e);
        }

        // Создаем таймер, который будет обновлять позицию изображения
        Timer timer = new Timer(30, e -> moveImage());
        timer.start();
    }

    private void moveImage() {
        // Перемещаем изображение по квадратно траектории
        switch (currentStep) {
            case 0: // Двигаемся вправо
                xPosition += speed;
                if (xPosition >= squareSize) {
                    xPosition = squareSize; // Устанавливаем его на границе
                    currentStep = 1; // Переходим к следующему шагу
                }
                break;
            case 1: // Двигаемся вниз
                yPosition += speed;
                if (yPosition >= squareSize) {
                    yPosition = squareSize;
                    currentStep = 2; // Переходим к следующему шагу
                }
                break;
            case 2: // Двигаемся влево
                xPosition -= speed;
                if (xPosition <= 0) {
                    xPosition = 0;
                    currentStep = 3; // Переходим к следующему шагу
                }
                break;
            case 3: // Двигаемся вверх
                yPosition -= speed;
                if (yPosition <= 0) {
                    yPosition = 0;
                    currentStep = 0; // Возвращаемся к началу
                }
                break;
        }

        repaint(); // Перерисовываем панель
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем изображение
        if (image != null) {
            g.drawImage(image, xPosition, yPosition, null);
        }
}
}
