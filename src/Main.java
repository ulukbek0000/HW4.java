import jdk.jshell.ImportSnippet;
        import java. util.Random;
           class hw_4 {
            public static int bossHealth = 900;
            public static int bossDamage = 50;
            public static String bossDefence;
            public static int[] heroesHealth = {290, 260, 200, 220};
            public static int[] heroesDamage = {25, 20, 15, 0};
            public static String[] heroesAttackType = {"Physical", "Magical", "Piercing", "Medic"};
            public static int roundNumber = 0;


            public static void main(String[] args) {
                showStatistics();
                while (!isGameOver()) {
                    playRound();
                }
            }

            public static boolean isGameOver() {
                if (bossHealth <= 0) {
                    System.out.println("Heroes won!!!");
                    return true;
                }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
                boolean allHeroesDead = true;

                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        allHeroesDead = false;
                        break;
                    }
                }
                if (allHeroesDead) {
                    System.out.println("Boss won!!!");
                }
                return allHeroesDead;
            }

            public static void chooseBossDefence() {
                Random random = new Random();
                int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
                bossDefence = heroesAttackType[randomIndex];
            }

            public static void playRound() {
                roundNumber++;
                chooseBossDefence();
                heroesAttack();
                healing();
                bossAttacks();
                showStatistics();
            }

            public static void bossAttacks() {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                        if (heroesHealth[i] < 0) {
                            heroesHealth[i] = 0;
                        }
                    }
                }
            }

            public static void heroesAttack() {
                for (int i = 0; i < heroesDamage.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        int damage = heroesDamage[i];
                        if (heroesAttackType[i] == bossDefence) {
                            Random random = new Random();
                            int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                            damage = heroesDamage[i] * coeff;
                            System.out.println("Critical damage: " + damage);
                        }
                        bossHealth = bossHealth - damage;
                        if (bossHealth < 0) {
                            bossHealth = 0;
                        }
                    }
                }
            }

            public static void healing() {
                if (heroesHealth[3] > 0) {
                    for (int i = 0; i < heroesAttackType.length; i++) {
                        if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && i != 3) {
                            heroesHealth[i] += 100;
                            System.out.println("Медик вылечил: " + heroesAttackType[i]);
                            break;
                        }
                    }
                }
            }


            public static void showStatistics() {
                System.out.println("ROUND " + roundNumber + " -------------");
        /*String defence;*       if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
                System.out.println("Boss health: " + bossHealth + " damage: "
                        + bossDamage + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
                for (int i = 0; i < heroesHealth.length; i++) {
                    System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: "
                            + heroesDamage[i]);
                }
            }


        }

