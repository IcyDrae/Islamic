using IslamicCli.Command;

namespace IslamicCli
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            Console.InputEncoding = System.Text.Encoding.UTF8;

            var handler = new Handler();
            await handler.Execute(args);
        }
    }
}
