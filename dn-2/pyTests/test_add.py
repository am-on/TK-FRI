import pexpect

def test_add_invalid_argument():
    baza = pexpect.pexpect()

    try:
        baza.send("add argument")
        baza.expect("Invalid argument")

        print("PASSED\ttest_add_invalid_argument")

    except:
        print("FAILED\ttest_add_invalid_argument")

    finally:
        baza.kill()

def test_add_one():
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

        print("PASSED\ttest_add_one")

    except:
        print("FAILED\ttest_add_one")

    finally:
        baza.kill()


def test_add_wrong_id():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("ID")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_wrong_id")

    except:
        print("FAILED\ttest_add_wrong_id")

    finally:
        baza.kill()

def test_add_empty_id():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_empty_id")

    except:
        print("FAILED\ttest_add_empty_id")

    finally:
        baza.kill()

def test_add_empty_name():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_empty_name")

    except:
        print("FAILED\ttest_add_empty_name")

    finally:
        baza.kill()

def test_add_empty_last_name():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("")

        baza.expect("Avg. grade: ")
        baza.send("8.3")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_empty_last_name")

    except:
        print("FAILED\ttest_add_empty_last_name")

    finally:
        baza.kill()

def test_add_wrong_grade():
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
        baza.send("grade")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_wrong_grade")

    except:
        print("FAILED\ttest_add_wrong_grade")

    finally:
        baza.kill()

def test_add_empty_grade():
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
        baza.send("")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_empty_grade")

    except:
        print("FAILED\ttest_add_empty_grade")

    finally:
        baza.kill()

def test_add_too_large_grade():
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
        baza.send("10.1")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_too_large_grade")

    except:
        print("FAILED\ttest_add_too_large_grade")

    finally:
        baza.kill()


def test_add_too_small_grade():
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
        baza.send("-0.1")

        baza.expect("Invalid input data")

        baza.send("count")
        baza.expect("No. of students: 0")

        print("PASSED\ttest_add_too_small_grade")

    except:
        print("FAILED\ttest_add_too_small_grade")

    finally:
        baza.kill()


def test_add_many():
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

        print("PASSED\ttest_add_many")

    except:
        print("FAILED\ttest_add_many")

    finally:
        baza.kill()


def test_add_duplicated_id():
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
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime1")

        baza.expect("Last name: ")
        baza.send("priimek1")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("Student already exists")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_add_duplicated_id")

    except:
        print("FAILED\ttest_add_duplicated_id")


def test_add_duplicated_name():
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
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("Student already exists")

        baza.send("count")
        baza.expect("No. of students: 1")

        print("PASSED\ttest_add_duplicated_name")

    except:
        print("FAILED\ttest_add_duplicated_name")


if __name__ == "__main__":
    test_add_invalid_argument()
    test_add_one()
    test_add_wrong_id()
    test_add_empty_id()
    test_add_empty_name()
    test_add_empty_last_name()
    test_add_empty_grade()
    test_add_wrong_grade()
    test_add_too_large_grade()
    test_add_too_small_grade()
    test_add_many()
    test_add_duplicated_id()
    test_add_duplicated_name()
