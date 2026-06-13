using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Controls;
using static IslamicCli.Command.Adhkar;

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
            var adhkar = new IslamicCli.Command.Adhkar();
            var result = adhkar.GetAllAdhkar();
            foreach (var item in result)
            {
                var textBlock = new TextBlock
                {
                    Text = $"{item.Text}: {item.Translation}",
                    Margin = new System.Windows.Thickness(5)
                };
                //AdhkarStackPanel.Children.Add(textBlock);
            }
        }
    }
}
