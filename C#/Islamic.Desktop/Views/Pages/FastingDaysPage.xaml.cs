using System.Windows.Controls;
using IslamicCli.Command;

namespace IslamicDesktop.Views.Pages
{
    public partial class FastingPage : UserControl
    {
        public FastingPage()
        {
            InitializeComponent();
            LoadFastingInfo();
        }

        private void LoadFastingInfo()
        {
            var fasting = new FastingDays();
            FastingText.Text = fasting.GetFastingInfo();
        }
    }
}
