import pexpect


def test_exit_after_use():
    baza = pexpect.pexpect()

    try:
        baza.send("count")
        baza.expect("No. of students: 0")

        baza.send("exit")
        baza.expect("Goodbye")
        print("PASSED\ttest_exit_after_use")

    except:
        print("FAILED\ttest_exit_after_use")

    finally:
        baza.kill()


def test_exit_on_start():
    baza = pexpect.pexpect()

    try:
        baza.send("exit")
        baza.expect("Goodbye")
        print("PASSED\ttest_exit_on_start")

    except:
        print ("FAILED\ttest_exit_on_start")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_exit_after_use()
    test_exit_on_start()
