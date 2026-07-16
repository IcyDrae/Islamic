using System.Globalization;
using System.Text;

namespace IslamicCli.Command
{
    public class Hijri
    {
        private readonly string[] HijriMonths =
        {
            "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani",
            "Jumada al-Ula", "Jumada al-Akhirah", "Rajab", "Sha'ban",
            "Ramadan", "Shawwal", "Dhul Qa'dah", "Dhul Hijjah"
        };

        private readonly HijriCalendar _hijri = new HijriCalendar();

        public Hijri()
        {
            _hijri.HijriAdjustment = -1;
        }

        public string GetHijriCalendar(bool useColors = false)
        {
            var today = DateTime.Now;
            int day = _hijri.GetDayOfMonth(today);
            int month = _hijri.GetMonth(today);
            int year = _hijri.GetYear(today);
            int daysInMonth = _hijri.GetDaysInMonth(year, month);

            var sb = new StringBuilder();
            sb.AppendLine(GetHeader(month, year));
            sb.AppendLine();
            sb.AppendLine(GetWeekDaysHeader());
            sb.Append(BuildCalendarDays(day, month, year, daysInMonth, useColors));

            var ramadan = BuildRamadanInfo();

            if (ramadan.isRamadan)
            {
                sb.AppendLine();
                sb.AppendLine($"Ramadan Mubarak! Day {ramadan.dayOfRamadan} of {ramadan.daysInMonth}");
            }
            else if (month < 9)
            {
                sb.AppendLine();
                sb.AppendLine($"Days until Ramadan: {ramadan.daysUntilRamadan}");
            }

            return sb.ToString();
        }

        private string GetHeader(int month, int year)
        {
            return $"{HijriMonths[month - 1]} {year} AH";
        }

        private string GetWeekDaysHeader()
        {
            return "Mo Tu We Th Fr Sa Su";
        }

        private string BuildCalendarDays(int today, int month, int year, int daysInMonth, bool useColors)
        {
            var sb = new StringBuilder();

            DateTime firstDay = _hijri.ToDateTime(year, month, 1, 0, 0, 0, 0);
            int start = ((int)firstDay.DayOfWeek + 6) % 7; // shift to Monday start

            sb.Append(new string(' ', start * 3));

            for (int d = 1; d <= daysInMonth; d++)
            {
                if (d == today)
                   sb.Append(useColors
                             ? $"\u001b[1;32m{d:00}\u001b[0m "
                             : $"[{d:00}] ");
                else
                    sb.Append($"{d:00} ");

                if ((start + d) % 7 == 0)
                    sb.AppendLine();
            }

            sb.AppendLine();
            return sb.ToString();
        }

        public (bool isRamadan, int dayOfRamadan, int daysInMonth, int daysUntilRamadan) BuildRamadanInfo()
        {
            var today = DateTime.Now;

            int day = _hijri.GetDayOfMonth(today);
            int month = _hijri.GetMonth(today);
            int year = _hijri.GetYear(today);

            int ramadanMonth = 9;
            int daysInMonth = _hijri.GetDaysInMonth(year, month);

            if (month < ramadanMonth)
            {
                int daysUntil = CalculateDaysUntilRamadan(day, month, year, ramadanMonth);
                return (false, 0, daysInMonth, daysUntil);
            }

            if (month == ramadanMonth)
            {
                return (true, day, daysInMonth, 0);
            }

            return (false, 0, daysInMonth, 0);
        }

        private int CalculateDaysUntilRamadan(int currentDay, int currentMonth, int year, int ramadanMonth)
        {
            int days = _hijri.GetDaysInMonth(year, currentMonth) - currentDay;
            for (int m = currentMonth + 1; m < ramadanMonth; m++)
            {
                days += _hijri.GetDaysInMonth(year, m);
            }
            return days;
        }

        public int GetCurrentHijriDay()
        {
            return _hijri.GetDayOfMonth(DateTime.Now);
        }

        public int GetCurrentHijriMonth()
        {
            return _hijri.GetMonth(DateTime.Now);
        }

        public int GetCurrentHijriYear()
        {
            return _hijri.GetYear(DateTime.Now);
        }

        public int GetDaysInCurrentMonth()
        {
            return _hijri.GetDaysInMonth(
                GetCurrentHijriYear(),
                GetCurrentHijriMonth());
        }

        public string GetCurrentMonthName()
        {
            return HijriMonths[GetCurrentHijriMonth() - 1];
        }

        public int GetMonthStartDayOfWeek()
        {
            var year = GetCurrentHijriYear();
            var month = GetCurrentHijriMonth();

            DateTime firstDay = _hijri.ToDateTime(year, month, 1, 0, 0, 0, 0);
            return ((int)firstDay.DayOfWeek + 6) % 7;
        }
    }
}
