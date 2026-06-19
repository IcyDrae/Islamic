using IslamicDesktop.Views.Pages;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Xml.Linq;

namespace IslamicDesktop.Views;

public partial class MainWindow : Window
{
    private string _activePage = "Prayer";

    public MainWindow()
    {
        InitializeComponent();
        MainContent.Content = new PrayerPage();
        SetActive("Prayer");
    }

    private void SetActive(string page)
    {
        _activePage = page;

        // refresh all buttons
        UpdateMenuStyles();
    }

    private void UpdateMenuStyles()
    {
        foreach (var child in LogicalTreeHelper.GetChildren(MenuPanel))
        {
            if (child is Button btn)
            {
                var key = btn.Tag?.ToString();

                if (key == _activePage)
                {
                    btn.Background = new SolidColorBrush(Color.FromRgb(201, 162, 74)); // gold
                    btn.Foreground = new SolidColorBrush(Colors.Black);
                    // Disable hover for active button
                    btn.IsHitTestVisible = false;
                }
                else
                {
                    btn.Background = Brushes.Transparent;
                    btn.Foreground = new SolidColorBrush(
                        Color.FromRgb(232, 230, 225));
                    btn.IsHitTestVisible = true;
                }
            }
        }
    }

    private void Prayer_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new PrayerPage();
        SetActive("Prayer");
    }

    private void Tomorrow_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new PrayerPage(showTomorrowsPrayers: true);
        SetActive("Tomorrow");
    }

    private void Dhikr_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new DhikrPage();
        SetActive("Dhikr");
    }

    private void Quran_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new QuranPage();
        SetActive("Quran");
    }

    private void Hijri_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new HijriPage();
        SetActive("Hijri");
    }

    private void Fasting_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new FastingPage();
        SetActive("Fasting");
    }

    private void Names_Click(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new NinetyNineNamesPage();
        SetActive("Names");
    }
}
