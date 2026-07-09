package com.lpg.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleUI {

	public static final String RESET = "\u001B[0m";

	public static final String BLACK  = "\u001B[30m";
	public static final String RED    = "\u001B[31m";
	public static final String GREEN  = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE   = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN   = "\u001B[36m";
	public static final String WHITE  = "\u001B[37m";

	public static final String BOLD  = "\033[0;1m";
	public static final String BLINK = "\u001B[5m";

	public static void slowPrint(String text, int delay) {
		for (char ch : text.toCharArray()) {
			System.out.print(ch);
			try { Thread.sleep(delay); } catch (Exception e) { e.printStackTrace(); }
		}
		System.out.println();
	}

	public static void sleep(int milliseconds) {
		try { Thread.sleep(milliseconds); } catch (Exception e) { e.printStackTrace(); }
	}

	public static void clearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	// ── Structural Frame ────────────────────────────────────────────────────────

	public static void drawPanelTop() {
		System.out.println(CYAN + "   ╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
	}

	public static void drawPanelBottom() {
		System.out.println(CYAN + "   ╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
	}

	public static void drawPanelDivider() {
		System.out.println(CYAN + "   ╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
	}

	public static void drawHeaderTitle(String title) {
		drawPanelTop();
		int totalWidth = 110;
		int titleLength = title.length();
		int padLeft  = (totalWidth - titleLength) / 2;
		int padRight = totalWidth - titleLength - padLeft;
		String formatStr = "   ║" + repeat(" ", padLeft) + YELLOW + BOLD + "%s" + RESET + CYAN + repeat(" ", padRight) + "║%n";
		System.out.printf(formatStr, title);
		drawPanelDivider();
	}

	public static void drawDataRow(String key, String value) {
		System.out.printf(CYAN + "   ║   " + GREEN + "%-30s" + CYAN + "│   " + WHITE + BOLD + "%-71s" + CYAN + "  ║%n" + RESET, key, value);
	}

	public static void drawMenuRow(String optionText) {
		System.out.printf(CYAN + "   ║        " + PURPLE + BOLD + "->  " + WHITE + "%-94s" + CYAN + "║%n" + RESET, optionText);
	}

	public static void drawInputFieldPrompt(String prompt) {
		System.out.print("\n" + CYAN + "   ┌───┤ " + YELLOW + BOLD + prompt + CYAN + " ├────────────────────────────────────────────" + "\n   └───> " + WHITE + BOLD);
	}

	// ── Star-pattern extras (matching * theme) ──────────────────────────────────

	public static void starDivider(String color) {
		System.out.println(color + BOLD + "   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" + RESET);
	}

	public static void starSectionHeader(String title, String color) {
		System.out.println();
		starDivider(color);
		int pad = (106 - title.length()) / 2;
		System.out.println(color + BOLD + "   *" + repeat(" ", pad) + YELLOW + title + color + repeat(" ", 106 - title.length() - pad) + "*" + RESET);
		starDivider(color);
		System.out.println();
	}

	public static void starMenuItem(int num, String label) {
		System.out.printf(CYAN + BOLD + "      *  " + RESET + YELLOW + BOLD + "[%d]" + RESET + "  " + WHITE + "%-90s" + CYAN + BOLD + "*%n" + RESET, num, label);
	}

	public static void starMenuItemStr(String key, String label) {
		System.out.printf(CYAN + BOLD + "      *  " + RESET + YELLOW + BOLD + "[%s]" + RESET + "  " + WHITE + "%-89s" + CYAN + BOLD + "*%n" + RESET, key, label);
	}

	public static void starMenuTop() {
		System.out.println(CYAN + BOLD + "      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" + RESET);
	}

	public static void starMenuBottom() {
		System.out.println(CYAN + BOLD + "      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" + RESET);
	}

	public static void starMenuEmpty() {
		System.out.println(CYAN + BOLD + "      *" + repeat(" ", 100) + "*" + RESET);
	}

	public static String repeat(String value, int count) {
		if (count <= 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(value.length() * count);
		for (int i = 0; i < count; i++) {
			builder.append(value);
		}
		return builder.toString();
	}

	// ── Animations & Graphics ───────────────────────────────────────────────────

	public static void showCombinedLandingScreen() {
		clearScreen();
		System.out.println(PURPLE + BOLD);

		String[] cylinderLines = { "                                                      ,,,,,,,,,,,,,,,,,,",
				"                                            ╣╬▒▒▒▒▒▒▒▒▒╬▓▓▓▓▓▓▓▓▓▓▓▓▓▓▀▀▀▀▓▓▓▓",
				"                                            ▀▓▓▓▓▓▓▓▓▓▓▓▓▓█████▓██▓▓▓▓▓▓▓▓▓▓▓╜",
				"                                              ▓▓▓▓▌  ║╢╢▓▓██████████▌   ▓▓▓▓",
				"                                              ▓▓▓▓▌  ║▒╫▓▓██████████▌  j▓▓▓▓",
				"                                              ▓██▓▌  ║▒▓▓▓▓█████████▌  j▓██▓",
				"                                              ▓█▓▓▓  ║╢▓▓█▓█████████▌  j▓▓▓▓",
				"                                              ▓██▓▓  ]▒▓▓▓▓█████████▌  ]▓██▓",
				"                                              ▓██▓▓  ]▒▓▓█▓█████████▌  ]▓██▓",
				"                                              ▓▓▓▓▓  ]▒╫▓▓█████▄█▐██▌  ]▓▓▓▓",
				"                                              ▐███▓╓╥▄▒▒▓▓▓██████████▄╦▐▓▓▓▓",
				"                                            ,╔▓▓▓▓▓▓▓▓▒▒╫▓██████████▓▓▓▓▓▓▓▓▓╗,",
				"                                         ╓@╬╣╬╣╢╣╣╣╫╣╢╢╣╢╫╣▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╗",
				"                                       ╖╢╢╢╢▒▒▒▒▒▒▒▒╣╣▓▒▓▒▓╢██▓▓╣▓▓█▓█▓█▓▓▓▓▓▓╬▒▓╣╬@,",
				"                                     ,╢╣╣▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓█▓▓▓▓╢▓▓▓▓▓▓▓▓▓▓▓▓▓Ç",
				"                                     ▓╣╣▓▓▓╣╣╣▒▒▒▒╢╬▓▓▓█▓▓▓█▓█▓██▓█▓▓▓▓▓▓▒▓╢▒▒▒▒╫╣╢╢▓▓",
				"                                     ▓▓▓▓▓▓╣▓▓▓▓▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒╢▒▒▒▒╫╣╣╣▓▓",
				"                                     ▓▓▓▓▓▓▓▓▓▓▓▒▒░░▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╣▒▒▒▒▒▒╫▓╣╣▓▓",
				"                                     ▓▓▓▓▓▓▓▓▓▓▓╬▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╣▒▒▒▒▒▒╫▓╣▓▓▓",
				"                                     ▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╣╢▒▒▒▒╢▓▓╣▓▓▓",
				"                                     ▓▓▓▓▓▓▓▓▓▓▓▓╣▒▒▒\u2560\u2560▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╢╣▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ╟▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ╟▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ╟▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                     ╟▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌",
				"                                     ▐▓▓▓▓▓▓▓▓▓▓▓▒▒▒▀▀▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▄▓▓▓▓▓▓▓▓▌",
				"                                     ]▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌",
				"                                     j▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌",
				"                                     ]▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▌",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓[",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓Γ",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓C",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓U",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                       ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╩",
				"                                        ▀▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓`",
				"                                          ╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╩",
				"                                          ¬╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓⌐",
				"                                           ╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
				"                                           \"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓▓",
				"                                              ▀▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▀╜",
				"                                                  ╙▀▀▀▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▀▀▀\"'" };

		for (String line : cylinderLines) {
			System.out.println(line);
			sleep(30);
		}
		System.out.println();

		System.out.println(BLUE + BOLD +
        "  ██╗      ████████╗  ██████╗      ██████╗  ██████╗  ██████╗ ██╗  ██╗██╗███╗   ██╗ ██████╗     ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗\n" +
        "  ██║      ██╔═══██║  ██╔════╝     ██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██║████╗  ██║██╔════╝     ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║\n" +
        "  ██║      ████████║  ██║  ███╗    ██████╔╝██║   ██║██║   ██║█████╔╝ ██║██╔██╗ ██║██║  ███╗    ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║\n" +
        "  ██║      ██╔═════╝  ██║   ██║    ██╔══██╗██║   ██║██║   ██║██╔═██╗ ██║██║╚██╗██║██║   ██║    ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║\n" +
        "  ███████╗ ██║        ╚██████╔╝    ██████╔╝╚██████╔╝╚██████╔╝██║  ██╗██║██║ ╚████║╚██████╔╝    ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║\n" +
        "  ╚══════╝ ╚═╝         ╚═════╝     ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝\n" + RESET);
		System.out.println(CYAN + "   ================================================================================================================" + RESET);
		slowPrint(YELLOW + BOLD + "                              LPG BOOKING SYSTEM" + RESET, 10);
		slowPrint(GREEN + "                     CVCORP LPG DISTRIBUTION SYSTEMS & REVENUE LOGISTICS" + RESET, 10);
		System.out.println(CYAN + "   ================================================================================================================" + RESET);
		System.out.println(YELLOW + BOLD + "   [Choose an option from 1 to 5 below]" + RESET);
		sleep(500);
	}

	// Compact banners for specific menu choices (register/login/admin)
	public static void showRegisterPattern() {
        clearScreen();
        System.out.println(BLUE + BOLD +
        "  ██████╗██╗   ██╗███████╗████████╗ ██████╗ ███╗   ███╗███████╗██████╗ \n" +
        " ██╔════╝██║   ██║██╔════╝╚══██╔══╝██╔═══██╗████╗ ████║██╔════╝██╔══██╗\n" +
        " ██║     ██║   ██║███████╗   ██║   ██║   ██║██╔████╔██║█████╗  ██████╔╝\n" +
        " ██║     ██║   ██║╚════██║   ██║   ██║   ██║██║╚██╔╝██║██╔══╝  ██╔══██╗\n" +
        " ╚██████╗╚██████╔╝███████║   ██║   ╚██████╔╝██║ ╚═╝ ██║███████╗██║  ██║\n" +
        "  ╚═════╝ ╚═════╝ ╚══════╝   ╚═╝    ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝" + RESET);
        System.out.println(BLUE + BOLD +
        "  ██████╗ ███████╗ ██████╗ ██╗███████╗████████╗███████╗██████╗ \n" +
        "  ██╔══██╗██╔════╝██╔════╝ ██║██╔════╝╚══██╔══╝██╔════╝██╔══██╗\n" +
        "  ██████╔╝█████╗  ██║  ███╗██║███████╗   ██║   █████╗  ██████╔╝\n" +
        "  ██╔══██╗██╔══╝  ██║   ██║██║╚════██║   ██║   ██╔══╝  ██╔══██╗\n" +
        "  ██║  ██║███████╗╚██████╔╝██║███████║   ██║   ███████╗██║  ██║\n" +
        "  ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝\n" + RESET);
    }

	public static void showLoginPattern() {
        clearScreen();
        System.out.println(GREEN + BOLD +
        "  ██████╗██╗   ██╗███████╗████████╗ ██████╗ ███╗   ███╗███████╗██████╗ \n" +
        " ██╔════╝██║   ██║██╔════╝╚══██╔══╝██╔═══██╗████╗ ████║██╔════╝██╔══██╗\n" +
        " ██║     ██║   ██║███████╗   ██║   ██║   ██║██╔████╔██║█████╗  ██████╔╝\n" +
        " ██║     ██║   ██║╚════██║   ██║   ██║   ██║██║╚██╔╝██║██╔══╝  ██╔══██╗\n" +
        " ╚██████╗╚██████╔╝███████║   ██║   ╚██████╔╝██║ ╚═╝ ██║███████╗██║  ██║\n" +
        "  ╚═════╝ ╚═════╝ ╚══════╝   ╚═╝    ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝" + RESET);
        System.out.println(GREEN + BOLD +
        "  ██╗      ██████╗  ██████╗ ██╗███╗   ██╗\n" +
        "  ██║     ██╔═══██╗██╔════╝ ██║████╗  ██║\n" +
        "  ██║     ██║   ██║██║  ███╗██║██╔██╗ ██║\n" +
        "  ██║     ██║   ██║██║   ██║██║██║╚██╗██║\n" +
        "  ███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║\n" +
        "  ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝\n" + RESET);
    }

	public static void showAdminLoginPattern() {
		clearScreen();
		System.out.println(PURPLE + BOLD +
        "   █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗\n" +
        "  ██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║\n" +
        "  ███████║██║  ██║██╔████╔██║██║██╔██╗ ██║\n" +
        "  ██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║\n" +
        "  ██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║\n" +
        "  ╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝\n" + RESET);
		System.out.println(PURPLE + BOLD +
        "  ██╗      ██████╗  ██████╗ ██╗███╗   ██╗\n" +
        "  ██║     ██╔═══██╗██╔════╝ ██║████╗  ██║\n" +
        "  ██║     ██║   ██║██║  ███╗██║██╔██╗ ██║\n" +
        "  ██║     ██║   ██║██║   ██║██║██║╚██╗██║\n" +
        "  ███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║\n" +
        "  ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝\n" + RESET);
	}

	public static void showAdminRegisterPattern() {
		clearScreen();
		System.out.println(PURPLE + BOLD +
        "   █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗\n" +
        "  ██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║\n" +
        "  ███████║██║  ██║██╔████╔██║██║██╔██╗ ██║\n" +
        "  ██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║\n" +
        "  ██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║\n" +
        "  ╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝\n" + RESET);
		System.out.println(BLUE + BOLD +
        "  ██████╗ ███████╗ ██████╗ ██╗███████╗████████╗███████╗██████╗ \n" +
        "  ██╔══██╗██╔════╝██╔════╝ ██║██╔════╝╚══██╔══╝██╔════╝██╔══██╗\n" +
        "  ██████╔╝█████╗  ██║  ███╗██║███████╗   ██║   █████╗  ██████╔╝\n" +
        "  ██╔══██╗██╔══╝  ██║   ██║██║╚════██║   ██║   ██╔══╝  ██╔══██╗\n" +
        "  ██║  ██║███████╗╚██████╔╝██║███████║   ██║   ███████╗██║  ██║\n" +
        "  ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝\n" + RESET);
    }

	public static void showExitPattern() {
		System.out.println(GREEN + BOLD +
        "  ████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗     ██╗   ██╗ ██████╗ ██╗   ██╗\n" +
        "  ╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝     ╚██╗ ██╔╝██╔═══██╗██║   ██║\n" +
        "     ██║   ███████║███████║██╔██╗ ██║█████╔╝       ╚████╔╝ ██║   ██║██║   ██║\n" +
        "     ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗        ╚██╔╝  ██║   ██║██║   ██║\n" +
        "     ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗        ██║   ╚██████╔╝╚██████╔╝\n" +
        "     ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝        ╚═╝    ╚═════╝  ╚═════╝\n" + RESET);
        System.out.println(YELLOW + BOLD + "                Thank you for using the LPG Booking System!" + RESET);
	}

	public static void loadingBar(String text) {
		System.out.print(YELLOW + BOLD + "   >> " + String.format("%-35s", text) + RESET + " ");
		for (int i = 0; i <= 20; i++) {
			System.out.print(GREEN + "\u2588" + RESET);
			try { Thread.sleep(25); } catch (Exception e) {}
		}
		System.out.println(GREEN + " [MOUNTED]" + RESET);
	}

	public static void bootAnimation() {
		System.out.println("\n" + BOLD + GREEN + "   [ESTABLISHING CLUSTER SUBSYSTEM DATA CORRIDORS]" + RESET);
		loadingBar("Loading Enterprise Matrix Storage");
		loadingBar("Resolving Ledger Transaction Routers");
		loadingBar("Connecting SQL Partition Pools");
		successBox("APPLICATION MATRIX ARMED SUCCESSFULLY");
		sleep(1000);
	}

	public static void successBox(String message) {
    int totalInsideWidth = 112;
    String leadIn = "SUCCESS : ";
    String fullContent = leadIn + message;
    
    // Dynamically calculate the exact padding needed to hit 112 characters
    int paddingLength = totalInsideWidth - fullContent.length()-1;
    if (paddingLength < 0) paddingLength = 0; // Prevent crash if message is huge
    
    System.out.println("\n" + GREEN + "   ┌" + repeat("─", totalInsideWidth) + "┐");
    System.out.println("   │ " + fullContent + repeat(" ", paddingLength) + "│");
    System.out.println("   └" + repeat("─", totalInsideWidth) + "┘" + RESET);
}

public static void errorBox(String message) {
    int totalInsideWidth = 112;
    String leadIn = "FAILURE : ";
    String fullContent = leadIn + message;
    
    int paddingLength = totalInsideWidth - fullContent.length();
    if (paddingLength < 0) paddingLength = 0;
    
    System.out.println("\n" + RED + "   ┌" + repeat("─", totalInsideWidth) + "┐");
    System.out.println("   │ " + fullContent + repeat(" ", paddingLength) + " │");
    System.out.println("   └" + repeat("─", totalInsideWidth) + "┘" + RESET);
}

	public static void receiptHeader() {
		drawHeaderTitle("OFFICIAL LPG BOOKING RECEIPT INVOICE");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		drawDataRow("Timestamp", LocalDateTime.now().format(formatter));
		drawDataRow("Issuing Authority", "Central Petroleum Board");
	}

	public static void paymentAnimation() {
		clearScreen();
		System.out.println(BOLD + YELLOW + "\n   [ROUTING SECURE CLEARING HOUSE TRANSFERS]\n" + RESET);
		for (int i = 0; i <= 100; i += 10) {
			System.out.print("\r   " + GREEN + "[" + progressBar(i) + "] " + i + "% SECURING CHANNEL" + RESET);
			try { Thread.sleep(120); } catch (Exception e) {}
		}
		System.out.println("\n");
		successBox("CLEARING HOUSE DEPOSIT ACKNOWLEDGED");
	}

	public static String progressBar(int percent) {
		StringBuilder bar = new StringBuilder();
		int completed = percent / 5;
		for (int i = 0; i < 20; i++) {
			bar.append(i < completed ? "\u2588" : "\u2591");
		}
		return bar.toString();
	}
}
