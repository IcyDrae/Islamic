using IslamicDesktop.Views.Pages;
using System.Windows;
using System.Xml.Linq;

namespace IslamicDesktop.Views;

public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
        MainContent.Content = new PrayerPage();
    }

    private void Prayer_Click(object sender, RoutedEventArgs e)
        => MainContent.Content = new PrayerPage();

    private void NextPrayer_Click(object sender, RoutedEventArgs e)
        => MainContent.Content = new PrayerPage(showNextOnly: true);

    private void Dhikr_Click(object sender, RoutedEventArgs e)
    {
        //return MainContent.Content = new DhikrPage();
    }

    private void Quran_Click(object sender, RoutedEventArgs e)
    {
        //MainContent.Content = new QuranPage();
    }

    private void Hijri_Click(object sender, RoutedEventArgs e)
    {
        //MainContent.Content = new HijriPage();
    }

    private void Fasting_Click(object sender, RoutedEventArgs e)
    {
        //MainContent.Content = new FastingPage();
    }

    private void Names_Click(object sender, RoutedEventArgs e)
    {
        //=> MainContent.Content = new NamesPage();
    }
}