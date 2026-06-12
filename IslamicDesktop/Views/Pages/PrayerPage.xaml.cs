using System.Windows.Controls;
using IslamicCli.Command.Prayer;

namespace IslamicDesktop.Views.Pages;

public partial class PrayerPage : UserControl
{
    public PrayerPage(bool showNextOnly = false)
    {
        InitializeComponent();

        if (showNextOnly)
        {
            LoadNext();
        } else
        {
            Load();
        }
    }

    private async void Load()
    {
        var pray = new Pray(new PrayerTimeService());
        var result = await pray.All();

        CityText.Text = $"City: {result.City}, {result.Country}";

        var times = result.Item1;

        FajrText.Text = $"Fajr: {times["Fajr"]}";
        DhuhrText.Text = $"Dhuhr: {times["Dhuhr"]}";
        AsrText.Text = $"Asr: {times["Asr"]}";
        MaghribText.Text = $"Maghrib: {times["Maghrib"]}";
        IshaText.Text = $"Isha: {times["Isha"]}";
    }

    private async void LoadNext()
    {
        var pray = new Pray(new PrayerTimeService());
        var result = await pray.Tomorrow();
        CityText.Text = $"City: {result.City}, {result.Country}";
        var times = result.Item1;
        FajrText.Text = $"Fajr: {times["Fajr"]}";
        DhuhrText.Text = $"Dhuhr: {times["Dhuhr"]}";
        AsrText.Text = $"Asr: {times["Asr"]}";
        MaghribText.Text = $"Maghrib: {times["Maghrib"]}";
        IshaText.Text = $"Isha: {times["Isha"]}";
    }
}