import pexpect


def test_count_empty():
    baza = pexpect.pexpect()

    try:
        baza.send("count")
        baza.expect("No. of students: 0")


        print("PASSED\ttest_count_empty")

    except:
        print("FAILED\ttest_count_empty")

    finally:
        baza.kill()


def test_count_invalid_argument():
    baza = pexpect.pexpect()

    try:
        baza.send("count argument")
        baza.expect("Invalid argument")

        print("PASSED\ttest_count_invalid_argument")

    except:
        print("FAILED\ttest_count_invalid_argument")

    finally:
        baza.kill()


def test_count_one():
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

        print("PASSED\ttest_count_one")

    except:
        print("FAILED\ttest_count_one")

    finally:
        baza.kill()

def test_count_many():
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

        print("PASSED\ttest_count_many")

    except:
        print("FAILED\ttest_count_many")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_count_empty()
    test_count_invalid_argument()
    test_count_one()
    test_count_many()
