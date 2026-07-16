using IslamicCli.Command;
using System.Windows;
using System.Windows.Controls;

namespace IslamicDesktop.Views.Pages
{
    public partial class DhikrPage : UserControl
    {
        public DhikrPage()
        {
            InitializeComponent();

            InsertAdhkar();
        }

        private void InsertAdhkar()
        {
            var adhkar = new Adhkar();
            var result = adhkar.GetAllAdhkar();

            if (result == null)
                return;

            foreach (var item in result)
            {
                var card = new Border
                {
                    Background = System.Windows.Media.Brushes.White,
                    CornerRadius = new CornerRadius(12),
                    Padding = new Thickness(15),
                    Margin = new Thickness(0, 0, 0, 15),
                    BorderThickness = new Thickness(1)
                };

                var stack = new StackPanel();

                stack.Children.Add(new TextBlock
                {
                    Text = item.Text,
                    FontSize = 22,
                    FontWeight = FontWeights.Bold,
                    TextWrapping = TextWrapping.Wrap
                });

                stack.Children.Add(new TextBlock
                {
                    Text = item.Translation,
                    FontSize = 16,
                    Margin = new Thickness(0, 8, 0, 0),
                    TextWrapping = TextWrapping.Wrap
                });

                string TimesCount = item.Count == 1 ? "Once" : item.Count.ToString() + " times";

                stack.Children.Add(new TextBlock
                {
                    Text = $"Repeat: {TimesCount}",
                    FontSize = 14,
                    Margin = new Thickness(0, 10, 0, 0),
                    FontWeight = FontWeights.SemiBold
                });

                card.Child = stack;

                AdhkarStackPanel.Children.Add(card);
            }
        }
    }
}
