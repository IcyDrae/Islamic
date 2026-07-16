using System.Windows.Controls;
using System.Windows.Media;
using System.Windows;
using IslamicCli.Command;
using IslamicCli.Data;

namespace IslamicDesktop.Views.Pages
{
    public partial class NinetyNineNamesPage : UserControl
    {
        public NinetyNineNamesPage()
        {
            InitializeComponent();
            LoadNames();
        }

        private void LoadNames()
        {
            var service = new NinetyNineNames();
            var names = service.GetAll();

            if (names == null)
                return;

            foreach (var name in names)
            {
                var card = new Border
                {
                    Width = 260,
                    Margin = new Thickness(10),
                    Padding = new Thickness(15),
                    CornerRadius = new CornerRadius(12),
                    Background = Brushes.White,
                    BorderBrush = new SolidColorBrush(Color.FromRgb(220, 210, 190)),
                    BorderThickness = new Thickness(1)
                };

                var stack = new StackPanel();

                stack.Children.Add(new TextBlock
                {
                    Text = name.Arabic,
                    FontSize = 24,
                    FontWeight = FontWeights.Bold,
                    Foreground = new SolidColorBrush(Color.FromRgb(15, 61, 62)),
                    TextAlignment = TextAlignment.Center
                });

                stack.Children.Add(new TextBlock
                {
                    Text = name.Transliteration,
                    FontSize = 14,
                    Margin = new Thickness(0, 5, 0, 0),
                    Foreground = Brushes.Gray,
                    TextAlignment = TextAlignment.Center
                });

                stack.Children.Add(new TextBlock
                {
                    Text = name.English,
                    FontSize = 14,
                    Margin = new Thickness(0, 5, 0, 0),
                    Foreground = Brushes.Black,
                    TextAlignment = TextAlignment.Center
                });

                card.Child = stack;
                NamesPanel.Children.Add(card);
            }
        }
    }
}