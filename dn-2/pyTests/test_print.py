import pexpect


def test_print_empty():
    baza = pexpect.pexpect()

    try:
        baza.send("print")
        baza.expect("No. of students: 0")


        print("PASSED\ttest_print_empty")

    except:
        print("FAILED\ttest_print_empty")

    finally:
        baza.kill()


def test_print_invalid_argument():
    baza = pexpect.pexpect()

    try:
        baza.send("print argument")
        baza.expect("Invalid argument")

        print("PASSED\ttest_print_invalid_argument")

    except:
        print("FAILED\ttest_print_invalid_argument")

    finally:
        baza.kill()


def test_print_one():
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

        baza.send("print")
        baza.expect("No. of students: 1")
        baza.expect("\t631 | priimek, ime | 8.3")

        print("PASSED\ttest_print_one")

    except:
        print("FAILED\ttest_print_one")

    finally:
        baza.kill()

def test_print_many():
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

        baza.send("print")
        baza.expect("No. of students: 3")
        baza.expect("\t631 | priimek, ime | 8")
        baza.expect("\t632 | priimek1, ime1 | 8")
        baza.expect("\t633 | priimek2, ime2 | 8")

        print("PASSED\ttest_print_many")

    except:
        print("FAILED\ttest_print_many")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_print_empty()
    test_print_invalid_argument()
    test_print_one()
    test_print_many()
