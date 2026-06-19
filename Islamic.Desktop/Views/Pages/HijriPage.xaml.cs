using System.Windows.Controls;
using System.Windows.Media;
using System.Windows;
using IslamicCli.Command;

namespace IslamicDesktop.Views.Pages
{
    public partial class HijriPage : UserControl
    {
        public HijriPage()
        {
            InitializeComponent();
            LoadCalendar();
            LoadRamadanInfo();
        }

        private void LoadCalendar()
        {
            var hijri = new Hijri();

            int currentDay = hijri.GetCurrentHijriDay();
            int daysInMonth = hijri.GetDaysInCurrentMonth();
            int startOffset = hijri.GetMonthStartDayOfWeek();

            MonthTitle.Text =
                $"{hijri.GetCurrentMonthName()} {hijri.GetCurrentHijriYear()} AH";

            CalendarGrid.Children.Clear();

            string[] headers = { "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su" };

            foreach (var h in headers)
            {
                CalendarGrid.Children.Add(new TextBlock
                {
                    Text = h,
                    FontWeight = FontWeights.Bold,
                    HorizontalAlignment = HorizontalAlignment.Center,
                    Margin = new Thickness(5)
                });
            }

            for (int i = 0; i < startOffset; i++)
            {
                CalendarGrid.Children.Add(new TextBlock
                {
                    Text = ""
                });
            }

            for (int day = 1; day <= daysInMonth; day++)
            {
                var border = new Border
                {
                    Margin = new Thickness(4),
                    Padding = new Thickness(10),
                    CornerRadius = new CornerRadius(6),
                    Background = day == currentDay
                        ? Brushes.Gold
                        : Brushes.White
                };

                border.Child = new TextBlock
                {
                    Text = day.ToString(),
                    HorizontalAlignment = HorizontalAlignment.Center
                };

                CalendarGrid.Children.Add(border);
            }
        }

        private void LoadRamadanInfo()
        {
            var hijri = new Hijri();
            var ramadan = hijri.BuildRamadanInfo();

            if (ramadan.isRamadan)
            {
                RamadanText.Text =
                    $"Ramadan Day {ramadan.dayOfRamadan} of {ramadan.daysInMonth}";
            }
            else
            {
                RamadanText.Text =
                    $"Days until Ramadan: {ramadan.daysUntilRamadan}";
            }
        }
    }
}
