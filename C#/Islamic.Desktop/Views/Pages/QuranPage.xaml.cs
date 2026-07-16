using IslamicCli.Command;
using IslamicCli.Data;
using IslamicCli.Utilities;
using System.Text;
using System.Windows.Controls;

namespace IslamicDesktop.Views.Pages
{
    public partial class QuranPage : UserControl
    {
        private readonly Quran _quran = new Quran();
     
        private List<QuranData> _surahs;

        private int _surahId = 0;

        public QuranPage(int surahId = 1)
        {
            InitializeComponent();

            _surahId = surahId;
            LoadSurahs();

            SurahSelector.SelectedValue = _surahId;
            
            LoadSurah(_surahId);
        }

        private void LoadSurah(int id)
        {
            var surah = _surahs.FirstOrDefault(s => s.Id == id);

            if (surah == null)
                return;

            var sb = new StringBuilder();

            sb.AppendLine($"{surah.Transliteration} - {surah.Translation}");
            sb.AppendLine();

            foreach (var ayah in surah.Verses)
            {
                sb.AppendLine($"{ayah.Id}. {ayah.Text}");
                sb.AppendLine($"{ayah.Translation}");
                sb.AppendLine();
            }

            QuranTextBlock.Text = sb.ToString();
        }

        private void LoadSurahs()
        {
            string resourceName = "Islamic.Cli.data.quran_en.json";
            var stream = EmbeddedResourceReader.GetAssemblyResource(resourceName);

            _surahs = EmbeddedResourceReader.ReadAssemblyToJson<QuranData>(stream);

            SurahSelector.ItemsSource = _surahs;
            SurahSelector.DisplayMemberPath = "Transliteration";
            SurahSelector.SelectedValuePath = "Id";
        }

        private void SurahSelector_Changed(object sender, SelectionChangedEventArgs e)
        {
            if (SurahSelector.SelectedItem is QuranData surah)
            {
                LoadSurah(surah.Id);
            }
        }
    }
}