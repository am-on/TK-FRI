import pexpect


def test_reset_empty():
    baza = pexpect.pexpect()

    try:
        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("y")
        baza.expect("OK")


        print("PASSED\ttest_reset_empty")

    except:
        print("FAILED\ttest_reset_empty")

    finally:
        baza.kill()


def test_reset_empty_cancel():
    baza = pexpect.pexpect()

    try:
        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("n")
        baza.expect("OK")


        print("PASSED\ttest_reset_empty_cancel")

    except:
        print("FAILED\ttest_reset_empty_cancel")

    finally:
        baza.kill()


def test_reset_invalid_argument():
    baza = pexpect.pexpect()

    try:
        baza.send("reset argument")
        baza.expect("Invalid argument")

        print("PASSED\ttest_reset_invalid_argument")

    except:
        print("FAILED\ttest_reset_invalid_argument")

    finally:
        baza.kill()


def test_reset_invalid_command():
    baza = pexpect.pexpect()

    try:
        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("command")
        baza.expect("Invalid command")


        print("PASSED\ttest_reset_invalid_command")

    except:
        print("FAILED\ttest_reset_invalid_command")

    finally:
        baza.kill()


def test_reset_one():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("y")
        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_reset_one")

    except:
        print("FAILED\ttest_reset_one")

    finally:
        baza.kill()


def test_reset_one_cancel():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("n")
        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_reset_one_cancel")

    except:
        print("FAILED\ttest_reset_one_cancel")

    finally:
        baza.kill()


def test_reset_many():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        # second
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("632")

        baza.expect("First name: ")
        baza.send("ime1")

        baza.expect("Last name: ")
        baza.send("priimek1")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 2")

        # third
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("633")

        baza.expect("First name: ")
        baza.send("ime2")

        baza.expect("Last name: ")
        baza.send("priimek2")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 3")

        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("y")
        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_reset_many")

    except:
        print("FAILED\ttest_reset_many")

    finally:
        baza.kill()


def test_reset_many_cancel():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        # second
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("632")

        baza.expect("First name: ")
        baza.send("ime1")

        baza.expect("Last name: ")
        baza.send("priimek1")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 2")

        # third
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("633")

        baza.expect("First name: ")
        baza.send("ime2")

        baza.expect("Last name: ")
        baza.send("priimek2")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 3")

        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("n")
        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 3")

        print("PASSED\ttest_reset_many_cancel")

    except:
        print("FAILED\ttest_reset_many_cancel")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_reset_empty()
    test_reset_empty_cancel()
    test_reset_invalid_argument()
    test_reset_invalid_command()
    test_reset_one()
    test_reset_one_cancel()
    test_reset_many()
    test_reset_many_cancel()
